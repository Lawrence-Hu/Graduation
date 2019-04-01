package cn.javaexception.service.impl;

import cn.javaexception.entity.User;
import com.alibaba.dubbo.config.annotation.Reference;
import order_module.entity.Order;
import order_module.service.OrderInterface;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import product_module.entity.Product;
import product_module.service.ProductInterface;
import utils.JsonData;

import java.util.List;
import java.util.Objects;

/**
 * @author hcuhao
 * @date 2019-03-12-10:08
 */
@Service
public class OrderInterfaceService {
    @Reference
    private OrderInterface orderInterface;
    @Reference
    private ProductInterface productInterface;
    /**
     * @author huchao 
     * @description 添加订单详情
     * @param  order 有可能order汇编等前端数据在进行操作
     * @return JsonData
     */
    public JsonData addToOrder(Order order) {
        //拿到当前用户
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        if (principal==null){
           return JsonData.buildError("用户未登录！");
        }
        //获取商品详情
        List<Order.OrderItem> items = order.getOrderItems();
        //计算价格
        float price = 0;
        for (Order.OrderItem item : items) {
            //得到产品信息
            Product product = productInterface.findProductById(item.getProductId());
            Integer num = item.getProductNum();
            if (product != null && num != null) {
                price += product.getShopPrice() * num;
            }else{
                items.remove(item);
            }
        }
        //数据封装
        order.setUserId(Objects.requireNonNull(principal).getId());
        order.setPrice(price);
        //调用接口
        boolean b = orderInterface.addToOrder(order);
        if (!b) {
           return JsonData.buildError("增加订单失败！");
        }
        return JsonData.buildSuccess("添加成功！");
    }
}