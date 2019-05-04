package cn.javaexception.shrio;

import cn.javaexception.entity.Permission;
import cn.javaexception.entity.Role;
import cn.javaexception.entity.RolePermission;
import cn.javaexception.entity.User;
import cn.javaexception.mapper.PermissionMapper;
import cn.javaexception.mapper.RoleMapper;
import cn.javaexception.mapper.RolePermissionMapper;
import cn.javaexception.mapper.UserMapper;
import cn.javaexception.util.FormatValidator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hcuhao
 * @date 2019-03-03-11:02
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    //授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("执行授权");
        Subject subject = SecurityUtils.getSubject();
        //获取当前用户
        User user = (User) subject.getPrincipal();
        //获取当前用户角色权限
        List<String> permissionIds = rolePermissionMapper.selectList(new QueryWrapper<RolePermission>().eq("role_id", user.getRoleId())).stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
        List<String> permissionNames = permissionMapper.selectList(new QueryWrapper<Permission>().in("id", permissionIds)).stream().map(Permission::getName).collect(Collectors.toList());
        //用户未冻结并且已认证
        if (user.getStatus().equals("1")) {
            //通过认证
            info.addStringPermissions(permissionNames);
            info.addRole(user.getRole().getRoleName());
        }
        return info;
    }

    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String username = token.getUsername();
        System.out.println(FormatValidator.isPhone(username));
        User user =null;
        if(FormatValidator.isEmail(username))
            user = userMapper.selectOne(new QueryWrapper<User>().eq("email", username));
        if(user==null && FormatValidator.isPhone(username)){
            user = userMapper.selectOne(new QueryWrapper<User>().eq("phone",username));
        }
        if (user==null)
            user = userMapper.selectOne(new QueryWrapper<User>().eq("account", username));

        if (user == null) {
            //用户名不存在
            return null;
        }
        //账户冻结
        if (!user.getStatus().equals("1")) {
            throw new LockedAccountException();
        }
        Role role = roleMapper.selectById(user.getRoleId());
        System.out.println(user);
        System.out.println(role);
        user.setRole(role);

        //判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
