package cn.javaexception.controller;

import cn.javaexception.entity.Order;
import cn.javaexception.entity.Product;
import cn.javaexception.service.OrderService;
import cn.javaexception.util.JsonData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author hcuhao
 * @date 2019-03-05-17:14
 */
@RequestMapping("/api/order")
@RestController
@RequiresRoles({"user"})
public class OrderController {
    /**
     * productId : 1
     * quantity : 2
     */
    @Autowired
    OrderService orderService;


    /**
     * orderDetail by page
     * @return
     */
    @PostMapping("/all")
    public JsonData orderDetail(String currentPage,String pageSize){

        return null;
    }

    /**
     * findOrderDetailByOrderId
     * @param orderId
     * @return
     */
    @PostMapping("/detail")
    public JsonData orderDetial(String orderId){

        return null;
    }

    /**
     * findAllOrderforSellerAll
     * @return
     */
    @PostMapping("/sell/all")
    public JsonData getSellOrderAll(){

        return null;
    }

    /**
     * findOrderDetialforSellerByOrderId
     * @param orderId
     * @return
     */
    @PostMapping("/sell/detail")
    public JsonData getSellDetailById(String orderId){
        return null;
    }

    @PostMapping("/addorder")
    public JsonData addOrder(@RequestBody @Valid Order order,Errors errors){
        if(errors.hasErrors()){
            return JsonData.buildError(errors.getFieldError().getDefaultMessage());
        }
        System.out.println(order);
        return orderService.addToOrder(order);
    }
}
