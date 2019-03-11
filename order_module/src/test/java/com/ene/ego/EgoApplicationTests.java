package com.ene.ego;


import com.ene.ego.beans.Order;
import com.ene.ego.beans.OrderItem;
import com.ene.ego.mapper.OrderItemMapper;
import com.ene.ego.mapper.OrderMapper;
import com.ene.ego.service.OrderItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
public class EgoApplicationTests {

//	@Autowired
//	private StatusMapper status;



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
    public void testupdate() {
        Order order1=new Order();
        order1.setNumber(1);
        order1.setPrice(1236);
        int i= order.updateById(order1);
        System.out.println(i);

    }


}