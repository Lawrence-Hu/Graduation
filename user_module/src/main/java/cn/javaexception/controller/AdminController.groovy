package cn.javaexception.controller


import cn.javaexception.service.AdminService
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
class AdminController {
    @Autowired
    AdminService adminService

    @GetMapping("/super")
    def test(String a){
        def pages = adminService.allUsersByPages
        return pages
    }
}
