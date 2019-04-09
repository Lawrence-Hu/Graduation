package cn.javaexception;

import cn.javaexception.service.AlipayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    AlipayService alipayService;
    @Test
    public void contextLoads() {
        String tradeStatusByTradeNo = alipayService.getTradeStatusByTradeNo("2019040922001420431000044233");
    }
}