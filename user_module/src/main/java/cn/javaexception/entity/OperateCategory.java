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
 * @since 2019-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OperateCategory extends Model<OperateCategory> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
