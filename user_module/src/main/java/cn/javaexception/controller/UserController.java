package cn.javaexception.controller;


import cn.javaexception.entity.LocalLogin;
import cn.javaexception.entity.User;
import cn.javaexception.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import utils.JsonData;

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
@RequiresRoles({"user"})
@RequestMapping("/api/user")
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
        System.out.println(SecurityUtils.getSubject().getPrincipal());

        return userService.sendEmailCode(user);
    }


}

