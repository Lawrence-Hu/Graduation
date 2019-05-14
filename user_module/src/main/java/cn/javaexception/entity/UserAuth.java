package cn.javaexception.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author huchao
 * @since 2019-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserAuth extends Model<UserAuth> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private String comments;

    private String address;

    private Boolean status;

    private Boolean isHandled;

    private String name;
    @TableField(exist = false)
    private List<UserAuthImg> imgs;
    private Date createdTime;
    @Override
    protected Serializable pkVal() {
        return null;
    }

}
