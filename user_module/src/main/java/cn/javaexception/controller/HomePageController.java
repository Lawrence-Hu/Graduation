package cn.javaexception.controller;

import cn.javaexception.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.JsonData;

/**
 * @author hcuhao
 * @date 2019-03-12-14:09
 */
@RestController
@RequestMapping("/api/home")
public class HomePageController {
    @Autowired
    HomeService homeService;
    public JsonData home(){
        return null;
    }
}
