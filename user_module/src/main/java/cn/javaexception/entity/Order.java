package cn.javaexception.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author JDR
 * @date 2019-04-01-14:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "order")
public class Order {

        private String id;//订单号
        private String userId;//用户id
        private Integer statusId;//订单状态id
        private Date time;//创建时间
        private Double price;//总价格

    @TableField(exist = false)
    private List<OrderItem> orderItems;//订单属性

    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName(value = "order_item")
    public static class OrderItem {
        @NotNull(message = "商品id不能为空！")
        private String productId;//物品id
        @NotNull(message = "商品数量不能为空！")
        private Integer productNum;//物品数量
        private String orderId;//订单号

    }
}
