package cn.javaexception.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import product_module.entity.Product;


/**
 * @author hcuhao
 * @date 2019-03-08-14:00
 */
@Mapper
public interface ProductInterfceMapper extends BaseMapper<Product> {
}
