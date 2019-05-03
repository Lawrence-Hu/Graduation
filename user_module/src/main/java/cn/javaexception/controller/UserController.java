package cn.javaexception.controller;


import cn.javaexception.entity.LocalLogin;
import cn.javaexception.entity.User;
import cn.javaexception.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
@RequiresRoles(value = {"user"})
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
    public JsonData setDefaultDeliverAddress(@RequestBody JSONObject data) throws HttpMessageNotReadableException {
        Object id = data.get("id");
        if(id==null){
            return JsonData.buildError("请求参数id无效!");
        }
        return userService.setDefaultDeliverAddress(new User().setDeliveryAddressId(id.toString()));
    }

    /**
     * @param user
     * @return JsonData
     * @author huchao
     * @description 设置手机号
     */
    @PostMapping("/setPhoneNumber")
    public JsonData setPhoneNumber(@RequestBody JSONObject data) {
        Object o = data.get("phone");
        if (o==null){
            return JsonData.buildError("请求参数id无效!");
        }
        return userService.setPhoneNumber(new User().setEmail(o.toString())) ? JsonData.buildSuccess() : JsonData.buildError("绑定失败");
    }

    /**
     * @param user
     * @return JsonData
     * @author huchao
     * @description 修改邮箱
     */
    @PostMapping("/setEmail")
    public JsonData setEmail(@RequestBody JSONObject data) {
        Object email = data.get("email");
        Object code = data.get("code");
        if (code==null||email==null){
            return JsonData.buildError("请求参数无效!");
        }
        return userService.setEmail(new User().setIdentifyingCode(code.toString()).setEmail(email.toString()));
    }

    /**
     * @param user
     * @return JsonData
     * @author huchao
     * @description 给邮箱发验证码
     */
    @PostMapping("/sendToEmailActivatingCode")
    public JsonData sendEmail(@RequestBody JSONObject data) {
        Object o = data.get("email");
        if (o==null){
            return JsonData.buildError("请求参数id无效!");
        }

        return userService.sendEmailCode(new User().setEmail(o.toString()));
    }


}

