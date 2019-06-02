package cn.javaexception.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProductAudit extends Model<ProductAudit> {
    private static final long serialVersionUID = 1L;
    private  String id;
    private String name;
    private String description;
    private Date createdTime;
    private Boolean isHandled;
    private Boolean status;
    private String productId;
    @TableField(exist = false)
    private List<AuditImg> imgs;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
