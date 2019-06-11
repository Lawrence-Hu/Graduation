package cn.javaexception.controller;


import cn.javaexception.entity.Product;
import cn.javaexception.service.ProductService;
import cn.javaexception.util.FileUploadUtils;
import cn.javaexception.util.JsonData;
import cn.javaexception.util.PageUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huchao
 * @since 2019-05-05
 */
@RestController
@RequestMapping("/api/product")
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

    @GetMapping("/all")
    public JsonData getProductsByPages(@Valid PageUtil pageUtil, Errors errors){
        if (errors.hasErrors()){
            return JsonData.buildError("分页参数错误！");
        }
        return productService.getProductsByPages(pageUtil);
    }
    @PostMapping("/update")
    public JsonData updateProducts(@RequestBody JSONObject object){
        Product product = JSON.toJavaObject(object, Product.class);
        return productService.updateById(product)?JsonData.buildSuccess("更新商品成功！"):JsonData.buildError("未做任何修改！请重试！");
    }
    @GetMapping("/audit")
    public JsonData productAudit(@Valid PageUtil pageUtil,Boolean isHandled, Errors errors){
       if(errors.hasErrors()){
         return JsonData.buildError(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage())  ;
       }
       return  productService.getAuditProductsByPages(pageUtil,isHandled);
    }
    @GetMapping("/audit/confirm")
    public JsonData auditComfirm(@RequestBody JSONObject object){
       if(object.get("product_id").toString().isBlank()|| object.get("audit_id").toString().isBlank()||object.get("is_passed").toString().isBlank()) {
           return JsonData.buildError("参数输入错误！");
       }
        return productService.updateAuditStatus(object);
    }
    @GetMapping("/upload")
    public JsonData uploadImg(MultipartFile[] files) {
        return FileUploadUtils.upload(files);
    }
}

