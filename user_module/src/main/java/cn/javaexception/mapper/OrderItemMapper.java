package cn.javaexception.mapper;

import cn.javaexception.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper extends BaseMapper<Order.OrderItem>{

}
