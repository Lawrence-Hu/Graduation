package com.ene.ego.interfaceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ene.ego.mapper.OrderInterfaceMapper;
import com.ene.ego.mapper.OrderItemInterfaceMapper;
import order_module.entity.Order;
import order_module.service.OrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import utils.JsonData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hcuhao
 * @date 2019-03-12-11:56
 */
public class OrderInterfaceImpl implements OrderInterface {
    @Autowired
    OrderInterfaceMapper orderInterfaceMapper;
    @Autowired
    OrderItemInterfaceMapper orderItemInterfaceMapper;


        /**
     * @author JDR
     * @description 查询订单与订单详情
     * @param  orderId
     * @return Order
     */
    @Override
    public JsonData selectById(String orderId) {

        if(orderId==null){

            return  JsonData.buildError("请输入订单号!");
        }
        Order order = orderInterfaceMapper.selectById(orderId);
        if(order==null)
        {
            return JsonData.buildError("请输入正确的订单号!");
        }
        List<Order.OrderItem> OrderItems = orderItemInterfaceMapper.selectList(new QueryWrapper<Order.OrderItem>().eq("order_id", orderId));
        order.setOrderItems(OrderItems);
        return JsonData.buildSuccess(order);
    }

        /**
     * @author JDR
     * @description 修改订单价格
     * @param  orderId,orderProce
     * @return Order
     */
    @Override
    public JsonData updatePriceById(String orderId, Float orderPrice) {

        if(orderId==null && orderPrice==null) {
            return JsonData.buildError("请输入订单号和修改的价格!");
        }
        Order order=orderInterfaceMapper.selectById(orderId);
        if(order==null)
        {
            JsonData.buildError("请输入正确的订单号!");
        }
        orderInterfaceMapper.updateById(order.setPrice(orderPrice));
        return JsonData.buildSuccess(order.getPrice(),"修改成功!");
    }

        /**
     * @author JDR
     * @description 删除订单
     * @param  orderId
     * @return Order
     */
    @Override
    public JsonData delOrder(String orderId) {
        if(orderId == null)
        {
            return  JsonData.buildError("请输入订单号!");
        }
        Order order=orderInterfaceMapper.selectById(orderId);
        if(order==null)
        {
            JsonData.buildError("请输入正确的订单号!");
        }
        int i= orderInterfaceMapper.deleteById(orderId);
        return i>0?JsonData.buildSuccess("删除成功!"): JsonData.buildError("系统异常,请稍后重试!");
    }
        /**
     * @author JDR
     * @description 修改订单状态
     * @param  orderId,statusId
     * @return Order
     */
    @Override
    public JsonData updateOrderSta(String orderId, String statusId) {

        if (orderId==null || statusId==null)
        {
            return JsonData.buildError("请输入订单号或选择订单状态!");
        }
        Order order = orderInterfaceMapper.selectById(orderId);
        int i=orderInterfaceMapper.updateById(order.setStatusId(statusId));
        return i>0?JsonData.buildSuccess("修改成功!"): JsonData.buildError("系统异常,请稍后重试!");
    }


    @Override
    public JsonData addToOrder(Order order) {
        //数据验证
        if(order==null || order.getOrderItems()==null){
            return JsonData.buildError("订单商品不能为空！");
        }
        int insert = orderInterfaceMapper.insert(order);
        AtomicInteger sum = new AtomicInteger();
        List<Order.OrderItem> orderItems = order.getOrderItems();
        orderItems.forEach((orderItem -> {
            int i = orderItemInterfaceMapper.insert(orderItem);
            if(i==1)
                sum.getAndIncrement();
        }));
        return insert==1&&sum.get()==orderItems.size()?JsonData.buildSuccess("新增订单成功！"):JsonData.buildError("新增订单失败！");
    }

    @Override
    public JsonData findOrderById(String id) {
        return null;
    }

    @Override
    public boolean changeOrderStatus(int i) {
        return false;
    }


//    @Override
//    public JsonData addToOrder(Order order) {
//        //数据验证
//        List<Order.OrderItem> orderItems = order.getOrderItems();
//        if (order==null) {
//            return JsonData.buildError("添加失败");
//        }
//        //判断用户是否存在
//        //判断商品价格是否合理
//        //.......
//        //生成订单号
//        String uuid = UUID.randomUUID().toString();
//        //插入订单号
//        int i = orderInterfaceMapper.insert(order.setId(uuid).setTime(LocalDateTime.now()));
//        int add = 0;
//        //得到items
//        for ( orderItem : orderItems) {
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
//

//    @Autowired
//    OrderItemInterfaceMapper orderItemInterfaceMapper;
//    @Autowired
//    OrderInterfaceMapper orderInterfaceMapper;
//
//
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
