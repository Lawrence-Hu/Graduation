package cn.javaexception.service;

import cn.javaexception.model.Cart;
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

    boolean addProduct(Cart cart);

    boolean deleteProduct(Cart[] carts);
}
