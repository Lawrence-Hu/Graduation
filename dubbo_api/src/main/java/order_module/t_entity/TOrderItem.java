package order_module.t_entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author hcuhao
 * @date 2019-03-12-11:48
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TOrderItem {
    private Integer productId;//物品id
    private Integer productNum;//物品数量
    private String orderId;//订单号

}