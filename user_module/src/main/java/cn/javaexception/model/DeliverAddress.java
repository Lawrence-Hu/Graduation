package cn.javaexception.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
public class DeliverAddress extends Model<DeliverAddress> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @NotNull
    private Integer userId;

    /**
     * 省id
     */
    @NotNull
    private Integer provinceId;

    /**
     * 城市id
     */
    @NotNull
    private Integer cityId;

    /**
     * 收货姓名
     */
    @NotNull
    private String name;

    /**
     * 家，公司，学校
     */
    @NotNull
    private String tag;

    /**
     * 手机号
     */
    @NotNull
    private String mobile;

    /**
     * 详细地址
     */
    @NotNull
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
