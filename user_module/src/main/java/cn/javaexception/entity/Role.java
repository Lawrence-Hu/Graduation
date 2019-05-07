package cn.javaexception.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
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
 * @since 2019-04-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String roleName;

    private String identity;

    private List<JSONObject> permissions;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
