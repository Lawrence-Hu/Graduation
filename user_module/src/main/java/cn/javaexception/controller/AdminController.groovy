package cn.javaexception.controller

import cn.javaexception.annotation.OperateAnnotation
import cn.javaexception.entity.OperateCategory
import cn.javaexception.entity.User
import cn.javaexception.entity.UserStatus
import cn.javaexception.service.AdminService
import cn.javaexception.service.OperateLogService
import cn.javaexception.service.UserRoleService
import cn.javaexception.service.UserService
import cn.javaexception.util.JsonData
import cn.javaexception.util.PageUtil
import com.alibaba.fastjson.JSONObject
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authz.annotation.Logical
import org.apache.shiro.authz.annotation.RequiresPermissions
import org.apache.shiro.authz.annotation.RequiresRoles
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

import static org.springframework.util.StringUtils.*

@RestController
@RequestMapping("/api/admin")
class AdminController {
    @Autowired
    AdminService adminService
    @Autowired
    UserService userService
    @Autowired
    OperateLogService operateLogService
    @Autowired
    UserRoleService userRoleService

    @GetMapping("/info/allUsers")
    @RequiresRoles(value=["admin"],logical = Logical.OR)
    @RequiresPermissions(value = ["admin:allUsers"])
    def getAllUsers(@Valid PageUtil pageUtil,Errors errors){
        if(errors.hasErrors()){
            return JsonData.buildError(errors.getFieldError().getDefaultMessage())
        }
        return adminService.getAllUsersByPages(pageUtil,null)
    }

    @GetMapping("/info")
    @RequiresRoles(value=["admin"],logical = Logical.OR)
    def getAdminInfo(){
        def object = SecurityUtils.getSubject().getPrincipal()
        return JsonData.buildSuccess(object)
    }

    @PostMapping("/user/update")
    @RequiresRoles(value ="admin")
    @RequiresPermissions(value = ["admin:updateUserInfo"])
    @OperateAnnotation(service=UserService.class,params = JSONObject.class,category = OperateCategory.user_info)
    def updateUserInfo(@RequestBody JSONObject param){
        def user = JSONObject.toJavaObject(param, User.class)
        //更新
        println "aaaa"
        def data =adminService.updateUserInfoById(user)
        return data
    }

    @PostMapping(value = "/user/changeStatus")
    @RequiresRoles(value ="admin")
    //@RequiresPermissions(value = ["admin:changeStatus"])
    @OperateAnnotation(service=UserService.class,params = JSONObject.class,category = OperateCategory.user_info)
    def changUserStatus(@RequestBody JSONObject data){
        def id = data.get("id")
        if(id==null){
            return JsonData.buildError("用户id不能为null")
        }
        //更新
        return adminService.changeUserStatusById(id as String, UserStatus.STATUS_OK)
    }

    @GetMapping(value = "/user/frozenUsers")
    @RequiresRoles(value ="admin")
    //@RequiresPermissions(value = ["admin:frozenUsers"])
    def getFrozenUsers(@Valid PageUtil pageUtil,Errors errors){

        if(errors.hasErrors()){
            return JsonData.buildError(errors.getFieldError().getDefaultMessage())
        }
        def users =adminService.getAllUsersByPages(pageUtil,UserStatus.STATUS_FROZEN)
        return users
    }
    @PostMapping("/addNewRole")
    def addNewRole(){

    }
    @GetMapping("/user/getRoles")
    def findUserRoleById(String id){
        if (id==null){
            return JsonData.buildError("用户id不能为null")
        }
        userRoleService.findUserRoleById(id)
    }
    @GetMapping("/user/getAllRoles")
    def findUserRoles(){
        userRoleService.findAllRoles()
    }

    @PostMapping("/user/deleteUserRole")
    def deleteUserRole(@RequestBody JSONObject params){
        if(isEmpty(params.get("user_id"))){
            return JsonData.buildError("用户ID不正确！")
        }
        userRoleService.deleteUserRole(params)
    }

    @PostMapping("/user/asignUserRole")
    def asignUserRole(@RequestBody JSONObject params){
        if(isEmpty(params.get("user_id"))){
            return JsonData.buildError("用户ID不正确！")
        }
        userRoleService.asginUserRole(params)
    }
    @GetMapping("/user/auth")
    def allUserAuthByPages(@Valid PageUtil pageUtil,Errors errors ){
        if(errors.hasErrors()){
            return  JsonData.buildError(errors.getFieldError().getDefaultMessage())
        }
        adminService.findUserAuthByPages(pageUtil)
    }

    @GetMapping("/user/auth/confirm")
    def authAudit(@RequestBody JSONObject params){
        if(params.get("id")==null||params.get("user_id")==null||params.get("isPassed")==null){
            return JsonData.buildError("参数不能为空！")
        }
        adminService.updateUserAuthStatus(params)
    }

    @GetMapping("/user/auth/handled")
    def allUserAuthHandledByPages(@Valid PageUtil pageUtil,Errors errors ){
        if(errors.hasErrors()){
            return  JsonData.buildError(errors.getFieldError().getDefaultMessage())
        }
        adminService.findUserAuthHandledByPages(pageUtil)
    }
}
