package com.ene.ego.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ene.ego.beens.Order;

public interface OrderService extends IService<Order> {

    int AddOrders(Order order);//添加订单

    Order SelectbyNum(int id);//查询订单



}
