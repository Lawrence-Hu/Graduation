package cn.javaexception.service.impl;

import cn.javaexception.model.Cart;
import cn.javaexception.mapper.CartMapper;
import cn.javaexception.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private CartMapper cartMapper;
    /**
     * @author huchao 
     * @description 增加到购物车
     * @param  cart
     * @return boolean
     */
    
    @Override
    public boolean addProduct(Cart cart) {
        if (cart == null) {
            return false;
        }
        return cart.setAddTime(LocalDateTime.now()).insert();
    }
    /**
     * @author huchao 
     * @description 删除购物车商品
     * @param  carts
     * @return boolean
     */
    @Override
    public boolean deleteProduct(Cart[] carts) {
        List<Integer> list = new ArrayList<>();
        Arrays.asList(carts).forEach(cart -> list.add(cart.getId()));
        int i = cartMapper.deleteBatchIds(list);
        return i > 0 ;
    }
}
