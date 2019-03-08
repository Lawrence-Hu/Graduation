package cn.javaexception.controller;


import cn.javaexception.model.LocalLogin;
import cn.javaexception.model.User;
import cn.javaexception.service.UserService;
import cn.javaexception.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
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
        return userService.setDefaultDeliverAddress(user);
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
    public JsonData setEmail(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return JsonData.buildError(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
        }
        return userService.setEmail(user);
    }

    /**
     * @param localLogin
     * @return JsonData
     * @author huchao
     * @description 用户注册
     */
    @PostMapping("/register")
    public JsonData register(@RequestBody LocalLogin localLogin, Errors errors) {
        System.out.println(localLogin);
        if (errors.hasErrors()) {
            return JsonData.buildError(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
        }
        return userService.register(localLogin);
    }

    /**
     * @param user
     * @return JsonData
     * @author huchao
     * @description 给邮箱发验证码
     */
    @PostMapping("/sendToEmailActivatingCode")
    public JsonData sendEmail(@RequestBody @Valid User user,Errors errors) {
        if (errors.hasErrors()) {
            return JsonData.buildError(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
        }
        return userService.sendEmailCode(user);
    }

    @GetMapping("/unAuth")
    public JsonData unAuth() {
        return JsonData.buildError(401, "您没有实名认证！请认证后重试");
    }

}

