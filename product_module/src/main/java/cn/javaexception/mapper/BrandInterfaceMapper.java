package cn.javaexception.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import product_module.entity.Brand;


@Mapper
public interface BrandInterfaceMapper extends BaseMapper<Brand> {
}
