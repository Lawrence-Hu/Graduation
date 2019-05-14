package cn.javaexception;

import cn.javaexception.entity.Img;
import cn.javaexception.entity.Product;
import cn.javaexception.service.impl.ProductServiceImpl;
import cn.javaexception.util.JsonData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
//    @Autowired
//    TestController testController;
    @Autowired
    private ProductServiceImpl productService;
    @Test
    public void test(){
//        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//        System.out.println(request.getRequestURL());
//        System.out.println(request.getRemotePort());
//        System.out.println(request.getSession());
//        System.out.println(request.getMethod());
//        System.out.println(request.getParameterNames());
//        System.out.println(testController);
//        testController.test();
//        System.out.println(OperateCategory.商品信息修改);
        Product product=new Product();
        List<Img> img = new ArrayList<>();
        Img img1 = new Img();
        Img img2 =new Img();

        img1.setImgUrl("zxc");
        img2.setImgUrl("hhh");
        img.add(img1);
        img.add(img2);

        product.setProductName("iphonex r ");
        product.setMarketPrice(new Float(2333));
        product.setProductsStock(11);
        product.setProductUnitId("1");
        product.setBrandId("1");
        product.setProductDesc("这是苹果xr");
        product.setUserId("test1");
        product.setImg(img);
       // System.out.println(productService);
        JsonData jsonData=productService.addProduct(product);

        System.out.println(jsonData);
        }

}
