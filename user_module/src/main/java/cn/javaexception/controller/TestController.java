package cn.javaexception.controller;

import cn.javaexception.util.JsonData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@Controller
public class TestController {
    @GetMapping("/test")
   // @OperateLog(value = "test",clazz = User.class)
    public @ResponseBody  JsonData test(){
        System.out.println("aaa");
        return JsonData.buildSuccess("OK");
    }
}
