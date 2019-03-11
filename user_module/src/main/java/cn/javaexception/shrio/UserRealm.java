package cn.javaexception.shrio;

import cn.javaexception.mapper.UserMapper;
import product_module.LocalLogin;
import product_module.User;
import cn.javaexception.service.LocalLoginService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * @author hcuhao
 * @date 2019-03-03-11:02
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private LocalLoginService localLoginService;
    @Autowired
    private UserMapper userMapper;

    //授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        //获取当前用户
        User user = (User) subject.getPrincipal();
        //用户未冻结并且已认证
        if (user.getCerification().equals("1") && user.getStatus().equals("0")) {
            //通过认证
            info.addStringPermission("user");
        }
        return info;
    }

    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        LocalLogin localLogin = new LocalLogin(Arrays.toString(token.getPassword()), token.getUsername());
        //查询是否有该用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("email", localLogin.getAccount())
                .or()
                .eq("phone", localLogin.getAccount())
                .or()
                .eq("account", localLogin.getAccount()));
        LocalLogin login;
        if (user == null) {
            //用户名不存在
            return null;
        }
        //账户冻结
        if (user.getStatus().equals("1")) {
            throw new LockedAccountException();
        }
        login = localLogin.selectOne(new QueryWrapper<LocalLogin>().eq("account", user.getAccount()));
        //判断密码
        return new SimpleAuthenticationInfo(user, login.getPassword(), getName());
    }
}
