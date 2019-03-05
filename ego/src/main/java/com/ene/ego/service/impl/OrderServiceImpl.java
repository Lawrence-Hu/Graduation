package com.ene.ego.service.impl;

import com.ene.ego.beens.Order;
import com.ene.ego.mapper.OrderMapper;
import com.ene.ego.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl  implements OrderService{

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public Order SelectbyNum(int id) {

        Order order = orderMapper.selectById(id);

        return order;
    }

    public Order UpdateOrSta(int id,int status) {

        Order order = orderMapper.selectById(id);
        order.setStatusId(status);
        return order;
    }

    @Override
    public int AddOrders(Order order) {

        int id=orderMapper.insert(order);
       // Order order1=orderMapper.selectById(id);
        return id;
    }


}
