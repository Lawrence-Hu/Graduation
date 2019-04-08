package cn.javaexception.service.impl;

import cn.javaexception.entity.User;
import cn.javaexception.service.OrderService;
import com.alibaba.dubbo.config.annotation.Reference;
import order_module.entity.Order;
import order_module.service.OrderInterface;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import product_module.entity.Product;
import product_module.service.ProductInterface;
import utils.JsonData;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Reference
    ProductInterface productInterface;
    @Reference
    OrderInterface orderInterface;

    @Override
    public JsonData addToOrder(List<Order.OrderItem> orderItems) {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        float price =0;
        for (Order.OrderItem orderVo : orderItems) {
            //获取商品
            JsonData jsonData = productInterface.findProductById(orderVo.getProductId());
            System.out.println(jsonData.getData());
            //商品是否存在
            if (jsonData.getCode() == -1 && jsonData.getData() == null) {
                return jsonData;
            }
            Product data = (Product) jsonData.getData();
            //不能买自己的商品
            if (data.getUserId().equals(principal.getId()))
                return JsonData.buildError("订单中不能有自己的商品！");
            //存货是否足够
            if (data.getProductsStock() < orderVo.getProductNum())
                return JsonData.buildError(data.getProductName() + "存货不足");
            //存货减少相应的方法

            price=price+data.getShopPrice()*orderVo.getProductNum();
            System.out.println(price);

        }
        return orderInterface.addToOrder(new Order().setPrice(price).setUserId(principal.getId()).setOrderItems(orderItems).setTime(LocalDateTime.now()));
    }
}
