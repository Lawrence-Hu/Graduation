package com.ene.ego.interfaceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ene.ego.mapper.OrderInterfaceMapper;
import com.ene.ego.mapper.OrderItemInterfaceMapper;
import order_module.entity.Order;
import order_module.service.OrderInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author hcuhao
 * @date 2019-03-12-11:56
 */
public class OrderInterfaceImpl implements OrderInterface {

    @Autowired
    OrderItemInterfaceMapper orderItemInterfaceMapper;
    @Autowired
    OrderInterfaceMapper     orderInterfaceMapper;


    /**
     * @author JDR
     * @description 查询订单与订单详情
     * @param  orderId
     * @return Order
     */
    @Override
    public Order selectById(String orderId) {

        if(orderId==null)
        {
            return null;
        }
        Order torder = orderInterfaceMapper.selectById(orderId);
        List<Order.OrderItem> orderItems = orderItemInterfaceMapper.selectList(new QueryWrapper<Order.OrderItem>().eq("order_id", orderId));
        return new Order().setOrderItems(orderItems);
    }

    @Override
    public boolean updatePriceById(Integer orderId, Integer orderPrice) {
        return false;
    }

    @Override
    public boolean delOrder(Integer orderId) {
        return false;
    }

    @Override
    public boolean updateOrderSta(Integer orderId, Integer statusId) {
        return false;
    }

    @Override
    public boolean addToOrder(Order order) {
        return false;
    }

//    /**
//     * @author JDR
//     * @description 查询订单与订单详情
//     * @param  orderId
//     * @return Order
//     */
//    @Override
//    public Order selectById(Integer orderId) {
//        if(orderId==null)
//        {
//            return null;
//        }
//        TOrder tOrder = orderInterfaceMapper.selectById(orderId);
//        List<TOrderItem> tOrderItems = orderItemInterfaceMapper.selectList(new QueryWrapper<TOrderItem>().eq("order_id", orderId));
//        return new Order().setOrderItems(tOrderItems).setTOrder(tOrder);
//    }
//
//    /**
//     * @author JDR
//     * @description 修改订单价格
//     * @param  orderId,orderProce
//     * @return Order
//     */
//    @Override
//    public boolean updatePriceById(Integer orderId, Integer orderPrice) {
//        if(orderId==null||orderPrice==null) {
//            return false;
//        }
//
//        TOrder tOrder = orderInterfaceMapper.selectById(orderId);
//        orderInterfaceMapper.updateById(tOrder.setPrice(orderPrice));
//
//        return true;
//    }
//
//    /**
//     * @author JDR
//     * @description 删除订单
//     * @param  orderId
//     * @return Order
//     */
//    @Override
//    public boolean delOrder(Integer orderId) {
//        if(orderId==null) {
//            return false;
//        }
//        orderInterfaceMapper.deleteById(orderId);
//        orderItemInterfaceMapper.delete(new QueryWrapper<TOrderItem>().eq("order_id", orderId));
//
//            return true;
//
//    }
//
//    /**
//     * @author JDR
//     * @description 修改订单状态
//     * @param  orderId,statusId
//     * @return Order
//     */
//    @Override
//    public boolean updateOrderSta(Integer orderId, Integer statusId) {
//        if (orderId==null||statusId==null)
//        {
//            return false;
//        }
//        TOrder tOrder = orderInterfaceMapper.selectById(orderId);
//        orderInterfaceMapper.updateById(tOrder.setStatusId(statusId));
//        return true;
//    }
//
//    /**
//     * @author huchao
//     * @description 增加订单
//     * @param  order
//     * @return boolean
//     */
//    @Override
//    public boolean addToOrder(Order order) {
//        //数据验证
//        List<TOrderItem> orderItems = order.getOrderItems();
//        if (order.getTOrder() == null || order.getTOrder().getUserId() == null || orderItems == null) {
//            return false;
//        }
//        //判断用户是否存在
//        //判断商品价格是否合理
//        //.......
//        //生成订单号
//        String uuid = UUID.randomUUID().toString();
//        //插入订单号
//        int i = orderInterfaceMapper.insert(order.getTOrder().setId(uuid).setTime(LocalDateTime.now()));
//        int add = 0;
//        //得到items
//        for (TOrderItem orderItem : orderItems) {
//            //插入item
//            int insert = orderItemInterfaceMapper.insert(orderItem.setOrderId(uuid));
//            if (insert == 1)
//                add++;
//        }
//        //判断是否插入成功
//        if (i == 1 && add == orderItems.size()) {
//            return true;
//        }
//        return false;
//    }
}
