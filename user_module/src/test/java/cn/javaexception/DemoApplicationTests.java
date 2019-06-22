package cn.javaexception;

import cn.javaexception.entity.Order;
import cn.javaexception.mapper.OrderMapper;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    @Autowired
    OrderMapper orderMapper;

    @Test
    public void test1(){
        Order order = new Order();
        order.setTime(new Date());
        order.setUserId("1564");
        order.setPrice(153.0);
        order.setStatusId(1);
        System.out.println(order);
        orderMapper.insert(order);
    }


}