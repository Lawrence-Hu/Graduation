package cn.javaexception.service.impl;

import cn.javaexception.model.ProductUnit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper extends BaseMapper<ProductUnit> {
}
