package cn.javaexception.mapper;

import cn.javaexception.entity.Product;
import cn.javaexception.entity.User;
import cn.javaexception.util.PageUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huchao
 * @since 2019-05-05
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    @Select(value ="select product_name,id,shop_price,stock,description,brand_id from product")
    @Results({
        @Result(column = "brand_id",property = "productBrand",one = @One(select = "cn.javaexception.mapper.ProductBrandMapper.selectById"))
    })
    Product getProduct();
}
