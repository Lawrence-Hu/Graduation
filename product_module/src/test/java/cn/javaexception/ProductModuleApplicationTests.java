package cn.javaexception;


import cn.javaexception.service.impl.ProductInterfaceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import product_module.entity.Product;
import utils.JsonData;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductModuleApplicationTests {

//    @Autowired
//    ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    ProductInterfaceImpl productInterface;
    @Test
    public void test() {
//        Product.Img img=new Product.Img();
//        img.setId(1).setImgUrl("asd").setProductId(1);
//        List<Product.Img> imgList=new ArrayList<>();
//        imgList.add(img);
//        Product product=new Product();
//        product.setImg(imgList);
//        product.setProductName("iphonex s max");
//        product.setMarketPrice(new Float(15));
//        product.setProductsStock(11);
//        product.setProductUnitId(1);
//        product.setBrandId(1);
//        product.setProductDesc("这是苹果xsmax");
//        JsonData jsonData = productInterface.addProduct(product);
//        System.out.println(jsonData);

        JsonData jsonData = productInterface.updateIsBestById("1", true);
        System.out.println(jsonData);

    }

}
