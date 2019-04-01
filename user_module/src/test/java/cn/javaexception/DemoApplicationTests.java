package cn.javaexception;

import cn.javaexception.mapper.UserMapper;
import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import product_module.service.ProductInterface;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Reference
    ProductInterface productInterfce;
//    @Test
//    public void contextLoads() {
//        String string = productInterfce.getString("sdskdj");
//        System.out.println(string);
//    }
}
