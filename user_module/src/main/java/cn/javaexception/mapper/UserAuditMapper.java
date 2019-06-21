package cn.javaexception.mapper;

import cn.javaexception.entity.User;
import cn.javaexception.entity.UserAudit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huchao
 * @since 2019-05-13
 */
@Mapper
public interface UserAuditMapper extends BaseMapper<UserAudit> {

    @SelectProvider(type = UserAuditSQL.class,method = "getUserAuditByPage")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "user_id",property = "user",one=@One(select = "cn.javaexception.mapper.UserMapper.selectUserById"))
    })
    List<UserAudit> getUserAuditByPage(Page<UserAudit> page,Boolean isHandled);

    class UserAuditSQL{
        public String getUserAuditByPage(){
            return new SQL(){{
                SELECT("*");
                FROM("user_audit");
                WHERE("is_handled=#{isHandled}");
            }} .toString();
        }
    }
}
