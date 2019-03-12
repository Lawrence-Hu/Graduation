package com.ene.ego.interfaceImpl;

import com.ene.ego.mapper.OrderInterfaceMapper;
import com.ene.ego.mapper.OrderItemInterfaceMapper;
import order_module.entity.Order;
import order_module.service.OrderInterface;
import order_module.t_entity.TOrderItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author hcuhao
 * @date 2019-03-12-11:56
 */
public class OrderInterfaceImpl implements OrderInterface {
    @Autowired
    OrderItemInterfaceMapper orderItemInterfaceMapper;
    @Autowired
    OrderInterfaceMapper orderInterfaceMapper;
    /**
     * @author huchao 
     * @description 增加订单
     * @param  order
     * @return boolean
     */
    @Override
    public boolean addToOrder(Order order) {
        //数据验证
        List<TOrderItem> orderItems = order.getOrderItems();
        if (order.getTOrder() == null || order.getTOrder().getUserId() == null || orderItems == null) {
            return false;
        }
        //判断用户是否存在
        //判断商品价格是否合理
        //.......
        //生成订单号
        String uuid = UUID.randomUUID().toString();
        //插入订单号
        int i = orderInterfaceMapper.insert(order.getTOrder().setId(uuid).setTime(LocalDateTime.now()));
        int add = 0;
        //得到items
        for (TOrderItem orderItem : orderItems) {
            //插入item
            int insert = orderItemInterfaceMapper.insert(orderItem.setOrderId(uuid));
            if (insert == 1)
                add++;
        }
        //判断是否插入成功
        if (i == 1 && add == orderItems.size()) {
            return true;
        }
        return false;
    }
}
