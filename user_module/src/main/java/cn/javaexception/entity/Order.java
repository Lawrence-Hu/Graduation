package cn.javaexception.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
@TableName(value = "orders")
public class Order {
    /**
     * 订单Id
     */
    @TableId
    private String id;
    /**
     * 用户Id
     */
    private String userId;
    private Integer statusId;//订单状态id
    private Date time;//创建时间
    private Double price;//总价格
    @Valid
    @TableField(exist = false)
    private List<OrderItem> orderItems;//订单属性

    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName(value = "order_item")
    public static class OrderItem {
        @TableId
        private String id;
        @NotEmpty(message = "商品id不能为空！")
        private String productId;//物品id
        @NotNull(message = "商品数量不能为空！")
        @Min(value = 1L,message = "数量非法！")
        private Integer productNum;//物品数量
        private String orderId;//订单号

    }
}
