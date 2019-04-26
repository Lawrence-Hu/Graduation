package cn.javaexception.controller

import cn.javaexception.entity.User
import cn.javaexception.service.AdminService
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import utils.JsonData

@RestController
@RequestMapping("/api/admin")
class AdminController {
    @Autowired
    AdminService adminService

    @GetMapping("/super")
    def test(String a){
        def pages = adminService.allUsersByPages
        println pages.properties
        def jsonSluper = new JsonSlurper()
        println(pages.records.class)
        pages.records<<new User()
        return JsonData.buildSuccess(pages.properties)
    }
}
