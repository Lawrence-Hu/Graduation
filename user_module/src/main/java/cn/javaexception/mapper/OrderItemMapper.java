package cn.javaexception.mapper;

import cn.javaexception.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderItemMapper extends BaseMapper<Order.OrderItem>{

    @InsertProvider(type = OrderItemMapperSQL.class,method = "addItems")
    void addItems(@Param("orderId") String orderId,@Param("items") List<Order.OrderItem> items);

    class OrderItemMapperSQL{
        public String addItems(Map map){
            List<Order.OrderItem> items = (List<Order.OrderItem>) map.get("items");
            StringBuilder sb = new StringBuilder();
            sb.append("insert into order_item");
            sb.append("(product_id,product_num,order_id)");
            sb.append("VALUES ");
            MessageFormat mf = new MessageFormat("( #'{'items[{0}].productId}, #'{'items[{0}].productNum},#'{'orderId})");
            for (int i = 0; i < items.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < items.size() - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
    }
}
