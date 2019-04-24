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
 * @since 2019-04-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AuthUser extends Model<AuthUser> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String authName;

    private String identity;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
