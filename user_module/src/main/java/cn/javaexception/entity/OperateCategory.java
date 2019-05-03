package cn.javaexception.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author huchao
 * @since 2019-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OperateCategory extends Model<OperateCategory> {

    @TableField(exist = false)
    private static final String user_info = "用户信息修改";
    @TableField(exist = false)
    private static final String product_info = "商品信息修改";
    @TableField(exist = false)
    private static final String user_auth = "用户权限修改";
    @TableField(exist = false)
    private static final String product_category = "上品种类添加";

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
