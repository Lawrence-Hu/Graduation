package cn.javaexception.controller;


import cn.javaexception.entity.Product;
import cn.javaexception.service.ProductService;
import cn.javaexception.util.FileUploadUtils;
import cn.javaexception.util.JsonData;
import cn.javaexception.util.PageUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
    @PostMapping("/audit/confirm")
    public JsonData auditComfirm(@RequestBody JSONObject object){
        System.out.println(object.toJSONString());
       if(object.getString("product_id").isBlank()|| object.getString("audit_id").isBlank()|| object.getBoolean("is_passed") == null) {
           return JsonData.buildError("参数输入错误！");
       }
        return productService.updateAuditStatus(object);
    }
    @GetMapping("/upload")
    public JsonData uploadImg(MultipartFile[] files) {
        return FileUploadUtils.upload(files);
    }
}

