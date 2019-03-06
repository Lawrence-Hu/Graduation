package com.ene.ego.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ene.ego.beens.Order;
import com.ene.ego.mapper.OrderMapper;
import com.ene.ego.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;//订单的mapper对象


    @Override
    public Order SelectbyNum(int id) {

        Order order = orderMapper.selectById(id);

        return order;
    }


    @Override
    public int AddOrders(Order order) {

        int id = orderMapper.insert(order);
        // Order order1=orderMapper.selectById(id);
        return id;
    }


}
