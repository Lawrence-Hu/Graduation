package cn.javaexception.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

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
public class OperateLog extends Model<OperateLog> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String operateCategoryId;

    @TableField("createdTime")
    private Date createdTime;

    private String opreaterId;

    private String beforeOperateData;

    private String afterOperateData;

    private String apiGateway;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
