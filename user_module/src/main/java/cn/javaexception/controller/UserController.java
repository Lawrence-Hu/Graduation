package cn.javaexception.controller;


import cn.javaexception.model.LocalLogin;
import cn.javaexception.model.User;
import cn.javaexception.service.UserService;
import cn.javaexception.util.JsonData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @param user
     * @return JsonData
     * @author huchao
     * @description 修改默认用户地址
     */
    @PostMapping("/setDefaultDeliverAddress")
    public JsonData setDefaultDeliverAddress(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return JsonData.buildError(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
        }
        return userService.setDefaultDeliverAddress(user) ? JsonData.buildSuccess() : JsonData.buildError("设置失败，请刷新后再试！");
    }

    /**
     * @param user
     * @return JsonData
     * @author huchao
     * @description 设置手机号
     */
    @PostMapping("/setPhoneNumber")
    public JsonData setPhoneNumber(@RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return JsonData.buildError(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
        }
        return userService.setPhoneNumber(user) ? JsonData.buildSuccess() : JsonData.buildError("绑定失败");
    }

    /**
     * @param user
     * @return JsonData
     * @author huchao
     * @description 修改邮箱
     */
    @PostMapping("/setEmail")
    public JsonData setEmail(@RequestBody User user, Errors errors) {
        return userService.setEmail(user)?JsonData.buildSuccess(0,null,"修改邮件成功"):JsonData.buildError("修改邮箱失败,验证码不正确，或邮箱已被绑定！！");
    }

    /**
     * @param localLogin
     * @return JsonData
     * @author huchao
     * @description 用户注册
     */
    @PostMapping("/register")
    public JsonData register(@RequestBody LocalLogin localLogin, Errors errors) {
        if (errors.hasErrors()) {
            return JsonData.buildError(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
        }
        //用户存在
        if (userService.getOne(new QueryWrapper<User>().eq("account", localLogin.getAccount())) != null) {
            return JsonData.buildError("注册失败！该账号已注册");
        }
        //保存用户以及登录密码
        return userService.register(localLogin) ? JsonData.buildSuccess("注册成功！") : JsonData.buildError("该账号已注册！");
    }

    @PostMapping("/sendToEmailActivatingCode")
    public JsonData sendEmail(@RequestBody User user) {
        return userService.sendEmailCode(user) ? JsonData.buildSuccess(0, null, "发送邮件成功") : JsonData.buildError("发送邮件失败！");
    }
}

