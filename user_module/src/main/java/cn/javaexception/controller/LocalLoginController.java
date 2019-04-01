package cn.javaexception.controller;


import cn.javaexception.mapper.UserMapper;
import cn.javaexception.entity.LocalLogin;
import cn.javaexception.entity.User;
import cn.javaexception.service.LocalLoginService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import utils.JsonData;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@RestController
@RequestMapping("/local")
public class LocalLoginController {
    @Autowired
    private LocalLoginService localLoginService;
    @Autowired
    private UserMapper userMapper;

    /**
     * @param localLogin
     * @return JsonData
     * @author huchao
     * @description 用户本地登录
     */

    @PostMapping("/login")
    public JsonData login(@RequestBody @Valid LocalLogin localLogin, Errors errors) {
        //数据校验
        if (errors.hasErrors()) {
            return JsonData.buildError(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
        }
        //shrio登录验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(localLogin.getAccount(), localLogin.getPassword());
        try {
            subject.login(token);
            //更新用户登录信息
            userMapper.update(null, new UpdateWrapper<User>().set("last_login_time", LocalDateTime.now())
                                                                   .eq("email", localLogin.getAccount())
                                                                   .or()
                                                                   .eq("phone", localLogin.getAccount())
                                                                   .or()
                                                                   .eq("account", localLogin.getAccount()));
            return JsonData.buildSuccess("登录成功");
        } catch (UnknownAccountException e) {
            return JsonData.buildError("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            return JsonData.buildError("用户密码错误");
        } catch (LockedAccountException e) {
            return JsonData.buildSuccess("用户已被冻结");
        }
    }

    /**
     * @return JsonData
     * @author huchao
     * @description 跳转登录界面
     */
    @GetMapping("/toLogin")
    public JsonData toLogin() {
        return JsonData.buildError("请登录！");
    }
}

