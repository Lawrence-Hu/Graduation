package cn.javaexception.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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
public class ProductUnit extends Model<ProductUnit> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private String id;

    /**
     * 单位
     */
    private String unit;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
