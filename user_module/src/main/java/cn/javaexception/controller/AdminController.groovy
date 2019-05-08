package cn.javaexception.controller

import cn.javaexception.annotation.OperateAnnotation
import cn.javaexception.entity.OperateCategory
import cn.javaexception.entity.User
import cn.javaexception.service.AdminService
import cn.javaexception.service.OperateLogService
import cn.javaexception.service.UserService
import cn.javaexception.util.PageUtil
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authz.annotation.Logical
import org.apache.shiro.authz.annotation.RequiresRoles
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import utils.JsonData

import javax.validation.Valid

@RestController
@RequestMapping("/api/admin")
class AdminController {
    @Autowired
    AdminService adminService
    @Autowired
    UserService userService
    @Autowired
    OperateLogService operateLogService

    @GetMapping("/info/allUsers")
    @RequiresRoles(value=["normalAdmin","superAdmin","verifyAdmin"],logical = Logical.OR)
    def getAllUsers(@Valid PageUtil pageUtil,Errors errors){
        if(errors.hasErrors()){
            return JsonData.buildError(errors.getFieldError().getDefaultMessage())
        }
        def users =adminService.getAllUsersByPages(pageUtil)
        return JsonData.buildSuccess(users)
    }

    @GetMapping("/info")
    @RequiresRoles(value=["normalAdmin","superAdmin","verifyAdmin"],logical = Logical.OR)
    def getAdminInfo(){
        def object = SecurityUtils.getSubject().getPrincipal()
        return JsonData.buildSuccess(object)
    }

    @PostMapping("/user/update")
    @RequiresRoles(value ="superAdmin")
    @OperateAnnotation(service=UserService.class,params = User.class,category = OperateCategory.user_info)
    def updateUserInfo(@RequestBody @Valid User user,Errors errors){
        if (errors.hasErrors()){
            return JsonData.buildError(errors.getFieldError().getDefaultMessage())
        }
         //如果权限不同不能修改
        def principal =(User) SecurityUtils.getSubject().getPrincipal()
       if(userService.getById(user.getId()).getRoleId()==userService.getById(principal.getId()).getRoleId()){
           return JsonData.buildError("您不能修改和你权限相同的用户!")
       }
        //更新
        def data =userService.updateUserInfoById(user)
        return data
    }
}
