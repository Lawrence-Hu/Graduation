package cn.javaexception.shrio;

import cn.javaexception.mapper.UserMapper;
import cn.javaexception.model.LocalLogin;
import cn.javaexception.model.User;
import cn.javaexception.service.LocalLoginService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
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
        return null;
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
                                                                 .eq("account",localLogin.getAccount()));
        LocalLogin login = null;
        if (user==null){
            //用户名不存在
            return null;
        }
        //账户冻结
        if (user.getStatus().equals("0")){
            throw  new LockedAccountException();
        }
        login = localLogin.selectOne(new QueryWrapper<LocalLogin>().eq("account", user.getAccount()));
        //判断密码
        return new SimpleAuthenticationInfo(user,login.getPassword(),getName());
    }
}
