package cn.javaexception.controller;


import cn.javaexception.service.impl.ProvinceServiceImpl;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import product_module.service.ProductInterface;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@RestController
@RequestMapping("/province")
public class ProvinceController {
   @Autowired
    ProvinceServiceImpl provinceService;
   @Reference
   ProductInterface productInterface;
    @GetMapping("/123")
    public void test(){
        System.out.println("controller : "+productInterface);
       // productInterface.findProductById(1);
       provinceService.test();
    }
}

