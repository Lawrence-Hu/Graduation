package com.ene.ego.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ene.ego.beens.Order;
import com.ene.ego.beens.Status;
import com.ene.ego.mapper.OrderMapper;
import com.ene.ego.mapper.StatusMapper;
import com.ene.ego.service.StatusService;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl extends ServiceImpl<StatusMapper, Status> implements StatusService{

    private OrderMapper orderMapper;

    public Order UpdateOrSta(int id, int status) {

        Order order = orderMapper.selectById(id);
        order.setStatusId(status);
        return order;
    }
}
