package cn.javaexception.service;

import cn.javaexception.model.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huchao
 * @since 2019-03-05
 */
public interface ProductService extends IService<Product> {

    boolean addProduct(Integer id);
}