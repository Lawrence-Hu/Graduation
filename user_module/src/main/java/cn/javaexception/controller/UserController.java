package cn.javaexception.controller;


import cn.javaexception.annotation.AvoidDuplicate;
import cn.javaexception.entity.User;
import cn.javaexception.mapper.UserMapper;
import cn.javaexception.service.UserService;
import cn.javaexception.util.JsonData;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@RestController
//@RequiresRoles(value = {"user"})
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    /**
     * @param data
     * @return JsonData
     * @author huchao
     * @description 修改默认用户地址
     */
    @PostMapping("/setDefaultDeliverAddress")
    //@AvoidDuplicate
    public JsonData setDefaultDeliverAddress(@RequestBody JSONObject data) throws HttpMessageNotReadableException {
        Object id = data.get("id");
        if(id==null){
            return JsonData.buildError("请求参数id无效!");
        }
        return userService.setDefaultDeliverAddress(new User().setDeliveryAddressId(id.toString()));
    }

    /**
     * @param data
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
     * @param data
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
     * @param data
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

    @PostMapping("/login")
    public JsonData login(@RequestBody @Valid JSONObject data, HttpServletResponse response) throws IOException {
        //数据校验
        if(data.get("account")==null||data.get("password")==null){
            return JsonData.buildError("用户账号或密码不能为null");
        }
        //shrio登录验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(data.get("account").toString(), data.get("password").toString(),true);
        try {
            subject.login(token);
            User principal = (User)subject.getPrincipal();
            userMapper.updateById(new User().setId(principal.getId()).setLastLoginTime(new Date()));
            String websoket_token = UUID.randomUUID().toString();
            String form_token = UUID.randomUUID().toString();
            response.setHeader("FORM-TOKEN",form_token);
            response.setHeader("WEBSOKET-TOKEN",websoket_token);

            redisTemplate.opsForValue().set(form_token,"handled");
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
     * @param data
     * @return JsonData
     * @author huchao
     * @description 用户注册
     */
    @PostMapping("/register")
    public JsonData register(@RequestBody JSONObject data) {
        String account = data.get("account").toString();
        String password = data.get("password").toString();

        if(account ==null||password==null){
            return JsonData.buildError("用户账号或密码不能为null");
        }
        return userService.register(account,password);
    }

    @GetMapping("/loginout")
    public JsonData loginOut(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipal()!=null){
            subject.logout();
            return JsonData.buildSuccess("退出登录成功");
        }
        return JsonData.buildError("用户未登录");
    }
    @GetMapping("/test")
    public JsonData test(){
        return JsonData.buildSuccess("OK");
    }
}

