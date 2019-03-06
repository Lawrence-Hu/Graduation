package com.ene.ego.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ene.ego.beens.OrderItem;
import com.ene.ego.mapper.OrderItemMapper;
import com.ene.ego.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
}
