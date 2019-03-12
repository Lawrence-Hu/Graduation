package order_module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import order_module.t_entity.TOrder;
import order_module.t_entity.TOrderItem;

import java.util.List;

/**
 * @author hcuhao
 * @date 2019-03-12-11:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Order {
    private TOrder tOrder = new TOrder();
    private List<TOrderItem> orderItems;//订单属性

}

