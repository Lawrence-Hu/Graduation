package cn.javaexception;

import cn.javaexception.entity.Img;
import cn.javaexception.entity.Product;
import cn.javaexception.service.impl.ProductServiceImpl;
import cn.javaexception.util.JsonData;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {
//    @Autowired
//    TestController testController;
//    @Autowired
//    private ProductServiceImpl productService;
    @Test
    public void test() throws CloneNotSupportedException {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.name ="huchao";
        users.add(user1);
        User user2 = (User)user1.clone();
        user2.setName("ouzhou");
        user1 = user2;
        System.out.println(users);
    }

}
@Data
@ToString
class User implements Cloneable{
    String name;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}