package cn.javaexception.mapper;

import cn.javaexception.entity.Product;
import cn.javaexception.util.PageUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

//    @Select(value = "SELECT product.id,product_name,market_price,shop_price,stock,is_sale,is_audit,is_hot,is_new,is_recom,sale_num,product.create_time,product_category.name,product_unit.name 'unit',sale_num,sale_time,user.name 'user_name' \n" +
//                     "from product,product_category,user,product_unit\n" +
//                     "where product.user_id = user.id and product.cata_id = product_category.id and product.id = product_unit.product_id limit #{page.currentPage},#{page.pageSize}")
//    JSONObject getProductByPages(PageUtil page);
}
