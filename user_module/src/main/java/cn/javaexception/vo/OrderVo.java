package cn.javaexception.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class OrderVo {

    /**
     * productId : 1
     * quantity : 2
     */
    @NotNull(message = "产品号Id不能为空！")
    private Integer productId;
    @NotNull(message = "数量不能为空！")
    @Min(value = 0,message = "不能为负数")
    private Integer quantity;

}
