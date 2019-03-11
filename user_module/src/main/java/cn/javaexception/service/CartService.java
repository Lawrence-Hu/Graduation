package cn.javaexception.service;

import product_module.Cart;
import cn.javaexception.util.JsonData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
public interface CartService extends IService<Cart> {

    JsonData addProduct(Cart cart);

    JsonData deleteProduct(Integer[] cartIds);
}
