package com.ene.ego.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import order_module.t_entity.TOrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemInterfaceMapper extends BaseMapper<TOrderItem> {
}
