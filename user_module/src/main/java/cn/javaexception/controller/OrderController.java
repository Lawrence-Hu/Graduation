package cn.javaexception.controller;

import order_module.entity.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.JsonData;

/**
 * @author hcuhao
 * @date 2019-03-05-17:14
 */
@RequestMapping("/order")
public class OrderController {
    @PostMapping("/add")
    public JsonData addToOrder(@RequestBody Order order){
        //order 信息验证
        return null;
    }


}
