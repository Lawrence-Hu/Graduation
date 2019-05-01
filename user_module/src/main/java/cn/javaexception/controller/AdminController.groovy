package cn.javaexception.controller

import cn.javaexception.entity.OperateLog
import cn.javaexception.entity.User
import cn.javaexception.service.AdminService
import cn.javaexception.service.OperateLogService
import cn.javaexception.service.UserService
import cn.javaexception.util.PageUtil
import com.alibaba.fastjson.JSON
import com.fasterxml.jackson.databind.ObjectMapper
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
    def updateUserInfo(@RequestBody User user){
         //如果权限不同不能修改
        def principal =(User) SecurityUtils.getSubject().getPrincipal()
       if(userService.getById(user.getId()).getRoleId()==userService.getById(principal.getId()).getRoleId()){
           return JsonData.buildError("您不能修改和你权限相同的用户!")
       }
        //保存用户信息
        def before = userService.selectUserInfo(user)

        //更新
        def data =userService.updateUserInfoById(user)
        //加入操作作日志
        if(data.getCode()==0){
            def after = userService.selectUserInfo(user)
            def id = principal.getId()
            OperateLog log = new OperateLog()
            //json转换
            log.setBeforeOperateData(JSON.toJSONString(before))
            log.setAfterOperateData(JSON.toJSONString(after))
            log.setCreatedTime(new Date())
            log.setOpreaterId(id)
            log.setOperateCategoryId("修改用户信息")
            operateLogService.save(log)
        }
        return data
    }
}
