package cn.javaexception.service.impl;

import cn.javaexception.entity.Order;
import cn.javaexception.entity.Product;
import cn.javaexception.entity.User;
import cn.javaexception.mapper.OrderMapper;
import cn.javaexception.mapper.OrderItemMapper;
import cn.javaexception.mapper.ProductMapper;
import cn.javaexception.service.OrderService;
import cn.javaexception.util.JsonData;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {

    private OrderMapper orderMapper;
    private ProductMapper productMapper;
    private OrderItemMapper orderItemMapper;
    @Override
    public JsonData addToOrder(Order order) {
        Double num = 0.00;
        //首先判断订单详情是否为空
        if(order.getOrderItems()==null)
        {
            return  JsonData.buildError("请正确提交订单！");
        }
//        //判断是否是当前用户,获取用户id
//        Subject subject = SecurityUtils.getSubject();
//        User principal = (User) subject.getPrincipal();
        //拿到订单详情
        List<Order.OrderItem> orderItemList=order.getOrderItems();
        for(Order.OrderItem orderItem:orderItemList){
            if(orderItem.getProductId() == null && orderItem.getProductNum()==null)
            {
                return JsonData.buildError("商品id和数量不能为空");
            }
            //查询详情中每个商品是否过审
            Product product = productMapper.selectById(orderItem.getProductId());
            if (!product.getIsAudit()){
                return JsonData.buildError("存在商品未过审，请检查");
            }
        //库存是否足够
            if(product.getStock()<orderItem.getProductNum())
            {
                return  JsonData.buildError("库存不足，请重试");
            }
            int temp=product.getStock()-orderItem.getProductNum();
         //库存足够,库存减去购买量
            productMapper.updateById(product.setStock(temp));
        //计算总价格并写入订单
            BigDecimal a1=new BigDecimal(Double.toString((product.getShopPrice())));
            BigDecimal b1=new BigDecimal(Double.toString((orderItem.getProductNum())));
            num+=a1.multiply(b1).intValue();;
        }
        order.setPrice(num);
        //order.setUserId(principal.getId());
        order.setTime(new Date());
        int i =orderMapper.insert(order);
        int add=0;
        for(Order.OrderItem orderItem:orderItemList)
        {
                int insert=orderItemMapper.insert(orderItem.setOrderId(order.getId()));
                if(insert==1){
                    add++;
                }
            //判断是否全部插入
            if (i == 1 && add == orderItemList.size()) {

                return JsonData.buildSuccess("下单成功");
            }
        }
        return JsonData.buildError("未知错误，请稍后重试");
    }
}
