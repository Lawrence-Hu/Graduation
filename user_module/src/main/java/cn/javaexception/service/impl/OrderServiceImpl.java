package cn.javaexception.service.impl;

import cn.javaexception.entity.Order;
import cn.javaexception.entity.Product;
import cn.javaexception.entity.User;
import cn.javaexception.mapper.OrderMapper;
import cn.javaexception.mapper.OrderItemMapper;
import cn.javaexception.mapper.ProductMapper;
import cn.javaexception.service.OrderService;
import cn.javaexception.service.ProductService;
import cn.javaexception.util.JsonData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huchao
 * @since 2019-05-05
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonData addToOrder(Order order) {
        Double num = 0.00;
        //首先判断订单详情是否为空
        if(order.getOrderItems()==null)
        {
            return  JsonData.buildError("请正确提交订单！");
        }
//        //判断是否是当前用户,获取用户id
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        //拿到订单详情
        List<Order.OrderItem> orderItemList=order.getOrderItems();
        List<String> itemsIds = order.getOrderItems().stream().map(Order.OrderItem::getProductId).collect(Collectors.toList());
        List<Product> products = productMapper.selectList(new QueryWrapper<Product>().eq("is_audit", true).in("id", itemsIds));
        System.out.println(products);
        if(itemsIds.size()!=products.size()){
            return  JsonData.buildError("存在商品未过审，请检查");
        }
        for (Product product : products) {
            for (Order.OrderItem item : orderItemList) {
                if(product.getId().equals(item.getProductId())){
                    if(product.getStock()<item.getProductNum()){
                                return JsonData.buildError("库存不足，请重试");
                    }else{
                       product.setStock(product.getStock()-item.getProductNum());
                        BigDecimal a1=new BigDecimal(Double.toString((product.getShopPrice())));
                        BigDecimal b1=new BigDecimal(Double.toString((item.getProductNum())));
                        num+=a1.multiply(b1).intValue();
                    }
                }
            }
        }
        order.setUserId(principal.getId());
        order.setTime(new Date());
        order.setPrice(num);

        orderMapper.insert(order);
        productService.updateBatchById(products);
        orderItemMapper.addItems(order.getId(),orderItemList);
        return JsonData.buildSuccess("下单成功！");
    }
}
