package com.ene.ego.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ene.ego.beens.Order;

public interface OrderService extends IService<Order> {

    int AddOrders(Order order);//添加订单

    Order SelectbyNum(int id);//查询订单

    boolean UpdatepriceById(Order order);//修改价格

    boolean delOrders(int id);//删除订单

    boolean UpdateOrSta(Order order);//修改订单状态
}
