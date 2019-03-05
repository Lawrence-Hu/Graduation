package com.ene.ego.controller;

import com.ene.ego.beens.Order;
import com.ene.ego.service.OrderService;
import com.ene.ego.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService os;

    @RequestMapping(value = "/sumorder", method = RequestMethod.POST)
    public JsonData SumOrder(@RequestBody Order order) {
        System.out.println(order);
        os.AddOrders(order);
        return order == null ? new JsonData(500, null, "下单失败") :
                new JsonData(200, null, "下单成功");
    }

    @RequestMapping(value = "/orderinquiry")
    public JsonData OrderInquiry(int id) {
        Order order = os.SelectbyNum(id);
        System.out.println(order);
        return null;
    }
}
