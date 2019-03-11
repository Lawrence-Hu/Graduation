package order_module.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class OrderItem {

        private Integer id;//订单ID
        private Integer  productId;//物品id
        private Integer productNum;//物品数量
        private Integer number;//订单号

}
