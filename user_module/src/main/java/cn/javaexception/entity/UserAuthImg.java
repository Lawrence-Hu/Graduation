package cn.javaexception.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
public class UserAuthImg extends Model<UserAuthImg> {

    private static final long serialVersionUID = 1L;

    private Date createdTime;
    private String authId;
    private String url;

    private String id;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
