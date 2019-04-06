package com.ene.ego;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ene.ego.mapper.OrderInterfaceMapper;
import com.ene.ego.mapper.OrderItemInterfaceMapper;
import order_module.entity.Order;
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

    @Test
    public void selectToOrder() {


    }

}