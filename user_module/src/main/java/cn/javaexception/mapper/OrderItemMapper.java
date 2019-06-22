package cn.javaexception.mapper;

import cn.javaexception.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderItemMapper extends BaseMapper<Order.OrderItem>{

    void addItems(@Param("orderId") String orderId,@Param("items") List<Order.OrderItem> items);
    class OrderItemMapperSQL{
        String addToOrder(Map map){
            Order order = (Order)map.get("order");
            List<Order.OrderItem> items = (List<Order.OrderItem>) map.get("items");
            StringBuilder sb = new StringBuilder();
            sb.append("insert into order_item");
            sb.append("(product_id,product_num,order_id)");
            sb.append("VALUES ");
            MessageFormat mf = new MessageFormat("(null, #{items[{0}].productId}, #{items[{0}].productNum},#{orderId})");
            for (int i = 0; i < items.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < items.size() - 1) {
                    sb.append(",");
                }
            }
            System.out.println(sb.toString());
//            return new SQL(){{
//                INSERT_INTO("order");
//                for (Order.OrderItem item : order.getOrderItems()) {
//                    VALUES("product_id,product_num,order_id","#{item.productId},#{item.productNum,#{order.id}");
//                }
//            }}.toString();
            return sb.toString();
        }
    }
}
