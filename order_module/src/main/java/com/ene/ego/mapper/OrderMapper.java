package com.ene.ego.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ene.ego.beans.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
