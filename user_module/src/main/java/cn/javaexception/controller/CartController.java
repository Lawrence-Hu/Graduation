package cn.javaexception.controller;


import cn.javaexception.entity.Cart;
import cn.javaexception.service.CartService;
import cn.javaexception.util.JsonData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@RestController
@RequiresRoles({"user"})
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * @param cart
     * @return JsonData
     * @author huchao
     * @description 添加到购物车
     */
    @PostMapping("/add")
    public JsonData addProductToCart(@RequestBody Cart cart, Errors errors) {
        return cartService.addProduct(cart);
    }

    /**
     * @param cartIds
     * @return JsonData
     * @author huchao
     * @description 删除购物车商品
     */
    @PostMapping("/delete")
    //考虑一下json格式
    public JsonData deleteProductsFromCart(@RequestBody String[] cartIds) {
        if (cartIds.length == 0) {
            JsonData.buildError("购物车id不能为空！");
        }
        return cartService.deleteProduct(cartIds);
    }

    /**
     * @param userId
     * @return JsonData
     * @author huchao
     * @description 购物车详情
     */
    @GetMapping("/detail")
    public JsonData getCartDetail(@RequestBody String userId) {
           return null;
    }
}

