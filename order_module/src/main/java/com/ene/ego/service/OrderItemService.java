package com.ene.ego.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ene.ego.beens.OrderItem;

public interface OrderItemService extends IService<OrderItem> {

    void addOrderItem(OrderItem orderItem);//添加订单详情

    boolean delOrderItem(OrderItem orderItem);//删除订单详情
}
