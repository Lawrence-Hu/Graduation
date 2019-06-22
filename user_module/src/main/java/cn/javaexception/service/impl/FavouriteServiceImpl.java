package cn.javaexception.service.impl;

import cn.javaexception.entity.Favourite;
import cn.javaexception.entity.Product;
import cn.javaexception.entity.User;
import cn.javaexception.mapper.FavouriteMapper;
import cn.javaexception.mapper.ProductMapper;
import cn.javaexception.service.FavouriteService;
import cn.javaexception.util.JsonData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class FavouriteServiceImpl extends ServiceImpl<FavouriteMapper, Favourite> implements FavouriteService {
    @Autowired
    private FavouriteMapper favouriteMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public JsonData addTofavourite(Favourite favourite) {
        //获取用户信息
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();

        //判断商品信息
        Product product = productMapper.selectById(favourite.getProductId());

        if (null == product) {
            return JsonData.buildError("没有该商品！");
        }
        //不能收藏自己的商品
        if (product.getUserId().equals(principal.getId())) {
            return JsonData.buildError("不能收藏自己的商品哦！");
        }
        //已经是否收藏了该商品
        if(null!=favouriteMapper.selectOne(new QueryWrapper<Favourite>()
                .eq("user_id",principal.getId())
                .eq("product_id",favourite.getProductId()))){
            return JsonData.buildError("你已经收藏了该商品呢！");
        }
        //增加商品热度
        productMapper.updateById(product.setHotIndex(product.getHotIndex()+1));
        //添加数据
        int i = favouriteMapper.insert(favourite.setTime(new Date()).setUserId(principal.getId()));

        return i > 0? JsonData.buildSuccess("添加成功！") : JsonData.buildError("添加失败！");
    }

    @Override
    public JsonData deleteFavouriteItems(String[] ids) {
        //获取用户信息
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        if (principal == null) {
            return JsonData.buildError("用户未登录！非法操作！");
        }
        //删除
        int delete = favouriteMapper.delete(new QueryWrapper<Favourite>().eq("user_id", principal.getId()).in("id", (Object) ids));

        if (delete < ids.length && delete > 0) {
            return JsonData.buildSuccess("只删除了部分数据！请确保数据完全正确！");
        }
        return delete == ids.length ? JsonData.buildSuccess("删除喜爱的产品成功！") : JsonData.buildError("删除失败！刷新下试试！");
    }
}
