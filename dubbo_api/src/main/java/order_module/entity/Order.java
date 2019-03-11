package order_module.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Order {
    private Integer number;//订单号

    private Integer userId;//用户id

    private Integer statusId;//订单状态id

    private Date time;//创建时间

    private float price;//总价格

    private List<OrderItem> list;//订单属性

}
