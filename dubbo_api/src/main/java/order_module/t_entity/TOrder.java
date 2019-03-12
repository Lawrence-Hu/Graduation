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
public class TOrder {
    private String id;//订单号
    private Integer userId;//用户id
    private Integer statusId;//订单状态id
    private LocalDateTime time;//创建时间
    private float price;//总价格
}