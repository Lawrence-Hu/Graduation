package cn.javaexception.controller;


import cn.javaexception.model.Cart;
import cn.javaexception.service.CartService;
import cn.javaexception.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    /**
     * @author huchao 
     * @description 添加到购物车
     * @param  cart
     * @return JsonData
     */
    @PostMapping("/add")
    public JsonData addProductToCart(@Valid @RequestBody  Cart cart) {
        return cartService.addProduct(cart) ? JsonData.buildSuccess("加入购物车成功！") : JsonData.buildError("加入购物车失败");
    }
    /**
     * @author huchao 
     * @description 删除购物车商品
     * @param carts 
     * @return JsonData
     */
    @PostMapping("/deleteProducts")
    public JsonData deleteProductsFromCart(@RequestBody Cart[] carts){
        return cartService.deleteProduct(carts)?JsonData.buildSuccess("移除商品成功"):JsonData.buildError("移除商品失败");
    }
    /**
     * @author huchao 
     * @description 购物车详情
     * @param userId
     * @return JsonData
     */
    @PostMapping("/detail")
    public JsonData getCartDetail(@RequestBody String userId){
        return null;
    }
}

