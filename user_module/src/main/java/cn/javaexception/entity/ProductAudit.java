package cn.javaexception.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Date createdTime;
    private Boolean isHandled;
    private Boolean status;
    @TableField(exist = false)
    private Product product;
    @JsonIgnore
    private String productId;
    private String auditUserId;
    private String comments;
    @TableField(exist = false)
    private List<AuditImg> imgs;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
