package order_module.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hcuhao
 * @date 2019-03-12-11:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Order {
    private String id;//订单号
    private Integer userId;//用户id
    private Integer statusId;//订单状态id
    private LocalDateTime time;//创建时间
    private float price;//总价格
    @TableField(exist = false)
    private List<OrderItem> orderItems;//订单属性

    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public static class OrderItem {
        private Integer productId;//物品id
        private Integer productNum;//物品数量
        private String orderId;//订单号

    }
}

