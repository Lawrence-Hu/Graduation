package com.ene.ego;


import com.ene.ego.beens.Order;
import com.ene.ego.beens.User;
import com.ene.ego.mapper.OrderMapper;
import com.ene.ego.mapper.UserMapper;
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
	public void testSelect1() {
		System.out.println(("----selectAll method test---"));
		List<Order> orderList = order.selectList(null);
		//Assert.assertEquals(5, statussList.size());
		orderList.forEach(System.out::println);
	}

	@Test
	public  void testAdd(){

		Order order1=new Order();
		order1.setUserId(3);
		order1.setStatusId(3);
		order1.setPrice(1202);
		order1.setTime(new Date());
		order.insert(order1);

	}
	@Test
	public void testselect(){

		Order order1=order.selectById(16);
		System.out.println(order1);

	}
}