package cn.javaexception.controller;

import cn.javaexception.service.AlipayService;
import com.alipay.api.AlipayApiException;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/alipay")
public class AlipayControllor {
    @Autowired
    private AlipayService alipayService;
    @GetMapping("/order")
    public void getPay(@RequestParam("orderId") String orderId, HttpServletResponse response){
        System.out.println(orderId);
        try {
            String pay = alipayService.pay(orderId);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(pay);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}