package com.ene.ego;


import com.ene.ego.beens.Order;
import com.ene.ego.beens.OrderItem;
import com.ene.ego.beens.User;
import com.ene.ego.mapper.OrderItemMapper;
import com.ene.ego.mapper.OrderMapper;
import com.ene.ego.mapper.UserMapper;
import com.ene.ego.service.OrderItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
public class EgoApplicationTests {

//	@Autowired
//	private StatusMapper status;


    @Autowired
    private UserMapper user;

    @Autowired
    private OrderMapper order;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemService orderItemService;

//	@Test
//	public void testSelect() {
//		System.out.println(("----selectAll method test---"));
//		List<Status> statussList = status.selectList(null);
//		//Assert.assertEquals(5, statussList.size());
//		statussList.forEach(System.out::println);
//	}


    @Test
    public void testSelect() {
        System.out.println(("----selectAll method test---"));
        List<User> userList = user.selectList(null);
        //Assert.assertEquals(5, statussList.size());
        userList.forEach(System.out::println);

    }

    @Test
    public void testupdate() {
        Order order1=new Order();
        order1.setNumber(1);
        order1.setPrice(1236);
        int i= order.updateById(order1);
        System.out.println(i);

    }

    @Test//订单详情删除测试 成功
    public  void testdelOrderItem(){
        OrderItem orderItem=new OrderItem();
        orderItem.setNumber(1);
       // int i=orderItemMapper.deleteByMap(orderItem);

       boolean i = orderItemService.delOrderItem(orderItem);
        System.out.println(i);

    }

}