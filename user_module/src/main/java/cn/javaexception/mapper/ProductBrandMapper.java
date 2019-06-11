package cn.javaexception.mapper;

import cn.javaexception.entity.ProductBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huchao
 * @since 2019-06-11
 */
@Mapper
public interface ProductBrandMapper extends BaseMapper<ProductBrand> {
    @Override
    ProductBrand selectById(Serializable id);
}
