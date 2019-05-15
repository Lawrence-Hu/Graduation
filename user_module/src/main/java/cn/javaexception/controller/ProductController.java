package cn.javaexception.controller;


import cn.javaexception.entity.Product;
import cn.javaexception.service.ProductService;
import cn.javaexception.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huchao
 * @since 2019-05-05
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

/**
 * @param
 * @return JsonData
 * @author JDR
 * @description 添加商品
 */
   @PostMapping("/addproduct")
    public JsonData addproduct(@RequestBody Product product){

       if(product==null)
       {
           return  JsonData.buildError("商品信息不能为Null");
       }
        return  productService.addProduct(product);
   }

   @PostMapping("/delproduct")
   public  JsonData delproduct(@RequestBody String[] productId){
        if(productId.length==0)
        {
            return  JsonData.buildError("商品id不能为空");
        }
        return productService.delProductById(productId);
   }
    @GetMapping("/test")
    public JsonData test(){
        return JsonData.buildSuccess("OK");
    }
}

