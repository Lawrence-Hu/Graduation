package cn.javaexception.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderVo {

    /**
     * productId : 1
     * quantity : 2
     */
    @NotNull(message = "产品号Id不能为空！")
    private Integer productId;
    @NotNull(message = "数量不能为空！")
    private Integer quantity;
}
