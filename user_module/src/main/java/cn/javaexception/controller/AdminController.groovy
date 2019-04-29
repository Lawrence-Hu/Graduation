package cn.javaexception.controller


import cn.javaexception.service.AdminService
import cn.javaexception.service.UserService
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authz.annotation.RequiresRoles
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import utils.JsonData

@RestController
@RequestMapping("/api/admin")
class AdminController {
    @Autowired
    AdminService adminService
    @Autowired
    UserService userService

    @PostMapping("/info/allUsers")
    @RequiresRoles(["normalAdmin"])
    def getAllUsers(@RequestBody params){
        def users = adminService.getAllUsersByPages(params)
        return JsonData.buildSuccess(users)
    }

    @GetMapping("/info")
    @RequiresRoles(["normalAdmin"])
    def getAdminInfo(){
        def object = SecurityUtils.getSubject().getPrincipal()
        return JsonData.buildSuccess(object)
    }
}
