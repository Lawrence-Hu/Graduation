package cn.javaexception.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
@Controller
public class TestController {
    @GetMapping("/test")
   // @OperateLog(value = "test",clazz = User.class)
    public void test(){
        System.out.println("test");
    }
}
