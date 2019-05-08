package cn.javaexception.controller;


import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@RestController
@RequiresRoles({"user"})
@RequestMapping("/api/oauthLogin")
public class OauthLoginController {

}

