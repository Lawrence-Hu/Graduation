package cn.javaexception.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import product_module.entity.Product;
@Mapper
public interface ProductInterfaceMapper extends BaseMapper<Product> {
}
