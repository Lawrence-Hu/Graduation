package cn.javaexception.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品id
     */
    @NotNull
    private Integer productId;

    /**
     * 供应用户id
     */
    @NotNull
    private Integer supplierId;

    /**
     * 数量
     */
    @NotNull
    private Integer quantity;

    @NotNull
    private String userId;
    /**
     * 加入购物车时间
     */
    private LocalDateTime addTime;
    /**
     * 购物车状态
     */
    private String cartStatus;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
