package cn.javaexception.mapper;

import cn.javaexception.entity.ProductAudit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductAuditMapper extends BaseMapper<ProductAudit> {

    @Select(value = "select * from product_audit where is_handled = #{isHandled}")
    @Results({
            @Result(column = "product_id",property = "product",one = @One(select = "cn.javaexception.mapper.ProductMapper.getProduct")),
            @Result(column = "id",property = "imgs",many = @Many(select = "cn.javaexception.mapper.AuditImgMapper.selectById"))
    })
    List<ProductAudit> getProductAudits(Page<ProductAudit> page, Boolean isHandled);
}
