package com.ene.ego.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.ene.ego.mapper.OrderInterfaceMapper;
import com.ene.ego.mapper.OrderItemInterfaceMapper;
import jdk.nashorn.internal.ir.annotations.Reference;
import order_module.entity.Order;
import order_module.entity.OrderItem;
import order_module.service.OrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import product_module.service.ProductInterfce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class OrderInterfaceImpl implements OrderInterface {

    @Autowired
    OrderInterfaceMapper orderInterfaceMapper;
    @Autowired
    OrderItemInterfaceMapper orderItemInterfaceMapper;
    @Reference
    ProductInterfce productInterfce;

    @Override
    public boolean AddOrders(Order order) {

        if(order.getUserId()==null) //判断用户id是否为空
        {
            return false;
        }
//        else if() 这个判断留给支付接口 确定订单状态是否付款
        orderInterfaceMapper.insert(order);
        return true;
    }

    @Override
    public boolean SelectbyNum(Integer id) {
        if(id.equals(""))//判断id是否为空
            return false;

        orderInterfaceMapper.selectById(id);
        return true;
    }

    @Override
    public boolean UpdatepriceById(Order order) {

        if (orderInterfaceMapper.selectById(order.getNumber())==null)//判断订单是否存在
        {
            return false;
        }
        else {
            orderInterfaceMapper.updateById(order);
            return true;
        }
    }

    @Override
    public boolean delOrders(Integer id) {

            orderInterfaceMapper.deleteById(id);
            return true;

    }

    @Override
    public boolean UpdateOrSta(Order order) {
        if(orderInterfaceMapper.selectById(order.getNumber())==null)//判断订单是否存在
        {
            return false;
        }
        else{
            orderInterfaceMapper.updateById(order);
            return true;
        }
    }

   @Override
    public boolean addOrderItem(Order order) {
        int i=0;
        List<OrderItem> list = order.getList();
        for (OrderItem item : list) {

            int insert = orderItemInterfaceMapper.insert(item);
            i=i+insert;
        }
        if(i==list.size())
        { return true;}
        else
            return false;
    }

    @Override
    public boolean delOrderItem(Integer id) {


        int i = orderItemInterfaceMapper.delete(new QueryWrapper<OrderItem>().eq("number", id));
         return true;
    }
}

