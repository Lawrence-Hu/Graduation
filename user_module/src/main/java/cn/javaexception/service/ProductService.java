package cn.javaexception.service;

import cn.javaexception.entity.Product;
import cn.javaexception.util.JsonData;
import cn.javaexception.util.PageUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huchao
 * @since 2019-05-05
 */
public interface ProductService extends IService<Product> {
    Product getProductInfoById(String id);
    JsonData addProduct(Product product);
    JsonData delProductById(String[] id);
    JsonData updateIsNewById(String id, Boolean type);//修改是否新品
    JsonData getProductsByPages(PageUtil page);
    JsonData getAuditProductsByPages(PageUtil pageUtil,Boolean isHandled);

    JsonData updateAuditStatus(JSONObject params);
}
