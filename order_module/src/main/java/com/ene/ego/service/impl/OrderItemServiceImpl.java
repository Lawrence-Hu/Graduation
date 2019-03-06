package com.ene.ego.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ene.ego.beens.Order;
import com.ene.ego.beens.OrderItem;
import com.ene.ego.mapper.OrderItemMapper;
import com.ene.ego.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    @Autowired
    private  OrderItemMapper orderItemMapper;//订单item的mapper 对象
    @Override//添加订单详情
    public void addOrderItem(OrderItem orderItem) {

    }

    @Override//删除订单详情
    public boolean delOrderItem(OrderItem orderItem) {

        Map<String,Object> ordItemMap= new HashMap<>();
        ordItemMap.put("number",orderItem.getNumber());

        int i=orderItemMapper.deleteByMap(ordItemMap);
        return i>0;

    }
}
