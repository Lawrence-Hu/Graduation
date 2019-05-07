package cn.javaexception.shrio;

import cn.javaexception.entity.*;
import cn.javaexception.mapper.*;
import cn.javaexception.util.FormatValidator;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Select;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private PermissionMapper permissionMapper;
    //授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("执行授权");
        Subject subject = SecurityUtils.getSubject();
        //获取当前用户
        User user = (User) subject.getPrincipal();
        List<String> permissions = permissionMapper.getPermissonsByUserId(user.getId()).stream().map(Permission::getName).collect(Collectors.toList());
        List<String> roles = roleMapper.getUserRolesByUserId(Collections.singletonList(user.getId())).stream().map(role -> (String)role.get("role_name")).collect(Collectors.toList());
        System.out.println(permissions);
        System.out.println(roles);
        info.addStringPermissions(permissions);
        info.addRoles(roles);
        return info;
    }

    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String username = token.getUsername();
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

        //判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
