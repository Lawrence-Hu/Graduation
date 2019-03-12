package cn.javaexception.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


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
@NoArgsConstructor
public class LocalLogin extends Model<LocalLogin> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @NotNull(message = "密码不呢为空")
    private String password;
    @NotNull(message = "用户名不能为空")
    private String account;

    public LocalLogin(@NotNull(message = "密码不呢为空") String password, @NotNull(message = "用户名不能为空") String account) {
        this.password = password;
        this.account = account;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
