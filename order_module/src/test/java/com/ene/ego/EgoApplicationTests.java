package com.ene.ego;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ene.ego.mapper.OrderInterfaceMapper;
import com.ene.ego.mapper.OrderItemInterfaceMapper;
import order_module.entity.Order;
import order_module.t_entity.TOrder;
import order_module.t_entity.TOrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
//@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
public class EgoApplicationTests {

    @Autowired
    OrderItemInterfaceMapper orderItemInterfaceMapper;
    @Autowired
    OrderInterfaceMapper orderInterfaceMapper;

//    @Test
//    public void addToOrder() {
//
//        Order order = new Order();
//        order.setTOrder(new TOrder().setPrice(15).setUserId(1).setTime(LocalDateTime.now()));
//        List<TOrderItem> tOrderItems = new ArrayList<>();
//        tOrderItems.add(new TOrderItem().setProductId(1).setProductNum(5));
//        tOrderItems.add(new TOrderItem().setProductId(2).setProductNum(15));
//        order.setOrderItems(tOrderItems);
//        //数据验证
//        List<TOrderItem> orderItems = order.getOrderItems();
//        if (order.getTOrder() == null || order.getTOrder().getUserId() == null || orderItems == null) {
//            return;
//        }
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
//            System.out.println("success");
//        }
 //   }

    @Test public void selectToOrder(){

       // TOrder tOrder = orderInterfaceMapper.selectById("87bbc1f8-d867-42a2-9567-f3162baa03c9");
        //List<TOrderItem> tOrderItems = orderItemInterfaceMapper.selectList(new QueryWrapper<TOrderItem>().eq("order_id", "87bbc1f8-d867-42a2-9567-f3162baa03c9"));
       // TOrder tOrder = orderInterfaceMapper.selectById("87bbc1f8-d867-42a2-9567-f3162baa03c9");
        //orderInterfaceMapper.updateById(tOrder.setPrice(40));
        //orderInterfaceMapper.deleteById("87bbc1f8-d867-42a2-9567-f3162baa03c9");
        //orderItemInterfaceMapper.delete(new QueryWrapper<TOrderItem>().eq("order_id", "87bbc1f8-d867-42a2-9567-f3162baa03c9"));
        //System.out.println(tOrder);
        TOrder tOrder = orderInterfaceMapper.selectById("96cc9fb2-1a75-4fe0-9f27-9f5579a024c6");
        orderInterfaceMapper.updateById(tOrder.setStatusId(2));

    }

}