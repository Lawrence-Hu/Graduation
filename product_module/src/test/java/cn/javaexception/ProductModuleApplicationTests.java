package cn.javaexception;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import product_module.entity.Product;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductModuleApplicationTests {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Test
    public void testadd() {
    }

}
