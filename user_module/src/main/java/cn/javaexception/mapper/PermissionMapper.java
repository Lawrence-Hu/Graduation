package cn.javaexception.mapper;

import cn.javaexception.entity.Permission;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huchao
 * @since 2019-05-03
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("select permission.name,permission.description from user,user_role,role,role_permission,permission " +
            "where user.id = user_role.user_id and user_role.role_id=role.id and role_permission.role_id = role.id and role_permission.permission_id=permission.id and user_id=#{id}")
    List<Permission> getPermissonsByUserId(String id);

    @Select({"<script>",
            "select user_role.role_id, permission.name,permission.description from user_role,role,role_permission,permission " +
                    "where user_role.role_id=role.id and role_permission.role_id = role.id and role_permission.permission_id=permission.id and user_role.role_id in ",
            "<foreach collection='list' index='index' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"})
    List<JSONObject> getPermissionByRoleId(List id);
}
