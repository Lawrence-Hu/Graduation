package cn.javaexception.service.impl

import cn.javaexception.entity.Role
import cn.javaexception.entity.User
import cn.javaexception.entity.UserRole
import cn.javaexception.mapper.PermissionMapper
import cn.javaexception.mapper.RoleMapper
import cn.javaexception.mapper.UserMapper
import cn.javaexception.mapper.UserRoleMapper
import cn.javaexception.service.UserRoleService
import cn.javaexception.util.JsonData
import cn.javaexception.util.PageUtil
import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class UserRoleServiceImpl  extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    UserMapper userMapper
    @Autowired
    RoleMapper roleMapper
    @Autowired
    UserRoleMapper userRoleMapper
    @Autowired
    PermissionMapper permissionMapper

    @Override
    def getAllUsersByRoles(PageUtil pageUtil) {
        JSONObject data = new JSONObject()
        Page<User> page = new Page<>(pageUtil.getCurrentPage(),pageUtil.getPageSize())
        //获取用户
        def users = userMapper.selectPage(page, new QueryWrapper<User>().select("id","name", "account","created_time"))
        def userIds = users.records.stream().map({ user -> user.getId() }).collect(Collectors.toList())
        def roles = roleMapper.getUserRolesByUserId(userIds).groupBy { role->role.get("user_id")}

        for (user in users.records){
            user.setRoles(roles.get(user.getId()))
            for(role in roles.get(user.getId())){
                role.remove("user_id")
                role.remove("role_name")
                role.remove("id")
            }
        }
        data.put("users",users.records)
        return data
    }

    @Override
    def findUserRoleById(String id) {
        def roles = roleMapper.getUserRolesByUserId(Collections.singletonList(id))
        def rolesIds = roles.stream().map({ role -> role.get("id") }).collect(Collectors.toList())
        if(rolesIds.isEmpty()){
            return JsonData.buildError("该用户没有任何角色！")
        }
        def permissions = permissionMapper.getPermissionByRoleId(rolesIds)
        def permissionRoles = permissions.groupBy { permission -> permission.get("role_id") }
        for (role in roles) {
            role.put("permissions", permissionRoles.get(role.get("id")))
        }
        return JsonData.buildSuccess(roles)
    }

    @Override
    def findAllRoles() {

        def roles = roleMapper.selectList()
        def rolesIds = roles.stream().map({ role -> role.getId() }).collect(Collectors.toList())
        def permissions = permissionMapper.getPermissionByRoleId(rolesIds)
        def permissionRoles = permissions.groupBy { permission -> permission.get("role_id") }
        for (role in roles) {
            role.setPermissions(permissionRoles.get(role.getId()))
        }
        return JsonData.buildSuccess(roles)
    }

    @Override
    def deleteUserRole(JSONObject params) {
        //TODO 权限一致不能修改！
        def delete = userRoleMapper.delete(new QueryWrapper<UserRole>()
                .eq("user_id", params.get("user_id") as String)
                .in("role_id",params.get("roleIds") as List<String>))
        delete>0?JsonData.buildSuccess("删除角色成功！"):JsonData.buildError("删除角色失败！")
    }

    @Override
    def asginUserRole(JSONObject params) {
        //TODO 权限一致不能修改！
        //数据封装
        def user_id = params.get("user_id") as String
        def roleIds = params.get("roleIds") as List<String>
        def userRoles = new ArrayList<UserRole>()
        //判断角色是否存在
        if(!(roleMapper.selectList(new QueryWrapper<Role>().in("id",roleIds)).size()==roleIds.size())){
            return JsonData.buildError("角色不存在！传入参数错误！")
        }
        //若有该角色 无法分配
        if(!userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("user_id",user_id).in("role_id",roleIds)).isEmpty()){
            println userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("user_id",user_id).in("role_id",roleIds))
            return JsonData.buildError("用户已有该角色！无法分配！")
        }
        roleIds.forEach({roleId->userRoles.add(new UserRole().setUserId(user_id).setRoleId(roleId))})
        return saveBatch(userRoles)?JsonData.buildSuccess("分配角色成功！"):JsonData.buildError("分配角色失败，请刷新后重试！")
    }
}
