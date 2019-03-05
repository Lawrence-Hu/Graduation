package com.ene.ego.service;

import com.ene.ego.beens.Order;

public interface OrderService {

        int AddOrders(Order order);//添加订单

        Order SelectbyNum(int id);//查询订单

        Order UpdateOrSta(int id,int status);//修改订单状态




}
