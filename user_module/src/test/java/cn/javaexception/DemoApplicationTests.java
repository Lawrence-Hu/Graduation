package cn.javaexception;

import cn.javaexception.vo.OrderVo;
import org.junit.Test;
import utils.Error;
import utils.ValidatorUtil;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {
    @Test
    public void contextLoads() {
        Error error = ValidatorUtil.validate(new OrderVo().setProductId(12).setQuantity(1));
        if (error.hasError()){
            System.out.println(error.getErrorInfo());
        }
    }
}