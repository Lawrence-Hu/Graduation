package cn.javaexception.mapper;

import cn.javaexception.entity.ProductAudit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface ProductAuditMapper extends BaseMapper<ProductAudit> {

    @SelectProvider(type = ProductAuditSQL.class,method = "getProductAudits")
    @Results({
            @Result(column = "product_id",property = "product",one = @One(select = "cn.javaexception.mapper.ProductMapper.getProduct")),
            @Result(column = "id",property = "imgs",many = @Many(select = "cn.javaexception.mapper.AuditImgMapper.selectById")),
            @Result(column = "id",property = "id")
    })
    List<ProductAudit> getProductAudits(Page<ProductAudit> page, Boolean isHandled);

    class ProductAuditSQL{
        public String getProductAudits(){
            return new SQL(){{
                SELECT("*");
                FROM("product_audit");
                WHERE("is_handled = #{isHandled}");
            }}.toString();
        }
    }
}
