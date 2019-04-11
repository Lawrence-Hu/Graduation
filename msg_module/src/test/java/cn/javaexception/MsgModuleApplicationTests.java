package cn.javaexception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgModuleApplicationTests {
    @Autowired
    TestPerson testPerson;
    @Test
    public void contextLoads() {
        testPerson.test();
        testPerson=null;
    }

}
