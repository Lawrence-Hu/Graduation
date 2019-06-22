package cn.javaexception.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class UserAudit extends Model<UserAudit> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private String comments;

    private String address;

    private String creditId;

    private Boolean status;

    private Boolean isHandled;

    private String auditUserId;
    @TableField(exist = false)
    private List<AuditImg> imgs;
    @TableField(exist = false)
    private User user;
    private Date createdTime;
    @Override
    protected Serializable pkVal() {
        return null;
    }

}
