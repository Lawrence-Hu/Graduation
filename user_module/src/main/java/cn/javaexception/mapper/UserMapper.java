package cn.javaexception.mapper;

import cn.javaexception.entity.User;
import cn.javaexception.util.JsonData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select id from user")
    List<String> getAllUserIds();

    @Select("select * from user")
    List<JSONObject> getUsers(Page page);

    @SelectProvider(type = UserMapperSQL.class,method = "selectAllUsers")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "roles",many = @Many(select = "cn.javaexception.mapper.RoleMapper.selectRoleById")),
            @Result(column = "status",property = "status"),
            @Result(column = "status",property = "userStatus",one = @One(select = "cn.javaexception.mapper.UserStatusMapper.selectById"))
    })
    List<User> selectAllUsers(Page<User> page, @Param("type") String type);

    class UserMapperSQL{
        public String selectAllUsers(@Param("type") String type){
            return new SQL(){{
                SELECT("id,name,account,phone,gender,address,email,created_time,status,last_login_time,certification,alipay_account");
                FROM("user");
                if(type!=null){
                    WHERE("status = #{type}");
                }
            }}.toString();
        }
    }
}
