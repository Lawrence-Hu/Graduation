package com.ene.ego.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import order_module.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInterfaceMapper extends BaseMapper<Order> {
}
