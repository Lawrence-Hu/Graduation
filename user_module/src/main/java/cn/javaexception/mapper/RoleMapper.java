package cn.javaexception.mapper;

import cn.javaexception.entity.Role;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
 * @since 2019-04-24
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select({"<script>",
            "select role.id, role.role_name,role.identity,user.id 'user_id'," + "role.description,role.created_time from user,user_role," + "role where user_role.role_id=role.id and user_role.user_id=user.id and user_id in",
                "<foreach collection='list' index='index' item='id' open='(' separator=',' close=')'>",
                    "#{id}",
                "</foreach>",
            "</script>"})
    List<JSONObject> getUserRolesByUserId(List id);

}
