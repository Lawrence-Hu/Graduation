package cn.javaexception.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Cart extends Model<Cart> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private String id;

    /**
     * 产品id
     */
    @NotNull(message = "产品号不能为null")
    private String productId;

    /**
     * 供应用户id
     */
    @NotNull(message = "供应用户id不能为空")
    private String supplierId;

    /**
     * 数量
     */
    @NotNull(message = "数量不能为空")
    private Integer quantity;

    @NotNull(message = "用户id不能为空")
    private String userId;
    /**
     * 加入购物车时间
     */
    private Date addTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
