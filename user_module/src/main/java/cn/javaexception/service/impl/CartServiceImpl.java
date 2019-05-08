package cn.javaexception.service.impl;

import cn.javaexception.mapper.UserMapper;
import cn.javaexception.entity.Cart;
import cn.javaexception.mapper.CartMapper;
import cn.javaexception.entity.User;
import cn.javaexception.service.CartService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_module.entity.Product;
import product_module.service.ProductInterface;
import utils.JsonData;

import java.time.LocalDateTime;
import java.util.Date;

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
    @Reference
    private ProductInterface productInterface;
    @Autowired
    private UserMapper userMapper;

    /**
     * @param cart
     * @return boolean
     * @author huchao
     * @description 增加到购物车
     */
    @Override
    public JsonData addProduct(Cart cart) {
        //判断用户是否是当前用户
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        //查询该商品
        JsonData jsonData = productInterface.findProductById(cart.getProductId());
        Product product;
        if (jsonData.getCode() != -1){
            product = (Product) jsonData.getData();
        }
        else
            return jsonData;
        //查询供应商
        User supplier = userMapper.selectById(product.getUserId());
        //查询购物车
        Cart dbCart = cartMapper.selectOne(new QueryWrapper<Cart>().eq("product_id", cart.getProductId()));
        //判断是否购物车已有该商品
        if (dbCart != null) {
            return JsonData.buildError("购物车里面已经有商品呢！");
        }
        //判断产品是否存在
        if (product == null) {
            return JsonData.buildError("该商品不存在呢！");
        }
        //判断数量是否足够
        if (product.getProductsStock() < cart.getQuantity()) {
            return JsonData.buildError("存库数量不足呢！");
        }
        //供应商不能为自己
        if (product.getUserId().equals(principal.getId())) {
            return JsonData.buildError("不能把自己的商品放入购物车哦！");
        }
        //判断供应商是否冻结或实名
        if (supplier.getStatus().equals("1")) {
            return JsonData.buildError("供应商账号异常呢！暂时不能购买！");
        }
        cart.setAddTime(new Date()).setSupplierId(supplier.getId()).setUserId(principal.getId());
        int insert = cartMapper.insert(cart);
        return insert > 0 ? JsonData.buildSuccess("添加购物车成功！") : JsonData.buildError("系统异常！请刷新后再试！");
    }

    /**
     * @param cartIds
     * @return boolean
     * @author huchao
     * @description 删除购物车商品
     */
    @Override
    public JsonData deleteProduct(String[] cartIds) {
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        int delete = 0;
        //获取所有Cart_id
        for (String id : cartIds) {
            int i = cartMapper.delete(new QueryWrapper<Cart>().eq("user_id", principal.getId()).eq("id", id));
            if (i == 1)
                delete++;
        }
        //前端数据有误
        if (0 < delete && delete < cartIds.length) {
            return JsonData.buildSuccess("只删除了部分数据,请检查传入参数是否正确！");
        }
        return delete == cartIds.length ? JsonData.buildSuccess("删除购物车商品成功！") : JsonData.buildError("删除失败！请检查参数是否正确！");
    }
}
