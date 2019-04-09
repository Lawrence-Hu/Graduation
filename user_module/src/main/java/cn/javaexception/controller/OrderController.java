package cn.javaexception.controller;

import cn.javaexception.service.OrderService;
import order_module.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.JsonData;

import javax.validation.Valid;
import java.util.List;

/**
 * @author hcuhao
 * @date 2019-03-05-17:14
 */
@RequestMapping("/api/order")
@RestController
public class OrderController {
    /**
     * productId : 1
     * quantity : 2
     */
    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public JsonData addToOrder(@RequestBody @Valid List<Order.OrderItem> order, Errors error){
        System.out.println(order);
        JsonData jsonData = orderService.addToOrder(order);
        return jsonData;
    }
}
