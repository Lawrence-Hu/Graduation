package cn.javaexception.controller;

import cn.javaexception.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.JsonData;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    HomeService homeService;
    @GetMapping("/CatAndCar")
    public JsonData getCategories(){
        return homeService.getCategoriesAndCarousels();
    }
}
