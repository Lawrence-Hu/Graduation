package cn.javaexception.controller;


import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huchao
 * @since 2019-04-24
 */
@RestController
@RequestMapping("/api/admin")
public class AuthUserController {

    @GetMapping("/test")
    @RequiresRoles(value = {"admin"})
    public String test(){
        return "test";
    }
}

