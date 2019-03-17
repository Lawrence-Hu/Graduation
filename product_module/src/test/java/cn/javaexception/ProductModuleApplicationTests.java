package cn.javaexception;


import cn.javaexception.mapper.ProductInterfceMapper;
import cn.javaexception.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import product_module.entity.Product;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductModuleApplicationTests {

    @Autowired
    //private ProductMapper productMapper;
    private ProductInterfceMapper productInterfceMapper;

    @Test
    public void testadd() {
        Product product=new Product();
        product.setProductName("小米8");
        productInterfceMapper.insert(product);
        System.out.println(product.getId());

    }

}
