package cn.javaexception.mapper;

import cn.javaexception.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    @SelectProvider(type = CartMapperSQL.class,method = "getCart")
    Cart getCart(String name);
    class CartMapperSQL{
        public String getCart(String name){
            return new SQL(){{
                    SELECT("*");
                    FROM("cart");
                    if(name!=null){
                        WHERE("name = {name}");
                    }
                }
            }.toString();
        }
    }
}
