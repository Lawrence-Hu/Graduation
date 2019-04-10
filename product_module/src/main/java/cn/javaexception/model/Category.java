package cn.javaexception.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author huchao
 * @since 2019-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Category extends Model<Category> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private String id;

    private String name;

    private String creatTime;

    private Boolean isOnPage;

    @TableField(exist = false)
    private List<CategoryItems> categoryItems;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
