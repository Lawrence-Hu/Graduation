package cn.javaexception.entity;

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
 * @since 2019-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserStatus extends Model<UserStatus> {

    public static final String STATUS_OK = "1";
    public static final String STATUS_FROZEN = "2";
    public static final String STATUS_ABNORMAL = "3";

    private static final long serialVersionUID = 1L;

    private String id;

    private String statusName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
