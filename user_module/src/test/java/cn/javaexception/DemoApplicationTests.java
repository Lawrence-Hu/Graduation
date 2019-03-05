package cn.javaexception;

import cn.javaexception.mapper.UserMapper;
import cn.javaexception.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    public void contextLoads() {
        User user  = new User();
        User user1 = user.selectById(1);
        System.out.println(user1);
        User user2 = userMapper.selectById(1);
        System.out.println(user2);

    }
}
