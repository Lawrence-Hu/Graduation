package cn.javaexception.controller;


import cn.javaexception.service.ProductService;
import cn.javaexception.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huchao
 * @since 2019-03-05
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

//    @PostMapping("/add")
//    public JsonData add(String id){
//        System.out.println(id);
//        boolean b = productService.addProduct(Integer.parseInt(id));
//        return b? JsonData.buildSuccess():JsonData.buildError("error");
//    }
}

