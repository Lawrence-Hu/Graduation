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


    @Override//查询订单
    public Order SelectbyNum(int id) {

        Order order = orderMapper.selectById(id);

        return order;
    }

    @Override//修改价格
    public boolean UpdatepriceById(Order order) {

        int i = orderMapper.updateById(order);

        return i >0 ;
    }

    @Override//删除订单
    public boolean delOrders(int id) {

        int i=orderMapper.deleteById(id);
        return i>0 ;
    }


    @Override
    public int AddOrders(Order order) {

        int id = orderMapper.insert(order);
        // Order order1=orderMapper.selectById(id);
        return id;
    }

    @Override//修改订单状态
    public boolean UpdateOrSta(Order order) {

        int i = orderMapper.updateById(order);
        return i>0;
    }

}
