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
 * @since 2019-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductCategory extends Model<ProductCategory> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String creatTime;

    private Boolean isOnPage;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
