package cn.javaexception.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2019-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SearchLogs extends Model<SearchLogs> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private String id;

    private Integer userId;

    private String keyword;

    private Date time;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
