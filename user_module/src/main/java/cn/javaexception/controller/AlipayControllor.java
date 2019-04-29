package cn.javaexception.controller;

import cn.javaexception.pay.AlipayConfig;
import cn.javaexception.service.AlipayService;
import cn.javaexception.util.RequestUtils;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequiresRoles({"user"})
@RequestMapping("/api/alipay")
public class AlipayControllor {
    @Autowired
    AlipayClient alipayClient;
    @Autowired
    AlipayConfig alipayConfig;
    @Autowired
    AlipayService alipayService;
    @GetMapping("/order")
    public void getPay(HttpServletResponse response,String orderId,HttpServletRequest request) throws AlipayApiException {
        // form表单生产
        String form = alipayService.pay(orderId);
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/refund")
    public void refund(){
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
        model.setAmount("5000");
        model.setOutBizNo("4562898655656565");
        model.setPayeeType("ALIPAY_LOGONID");
        model.setPayeeAccount("tukheo4380@sandbox.com");
        request.setBizModel(model);
        try {
            AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/callback")
    public void aplipayCallback(HttpServletRequest request){
        //获取参数
        Map map = RequestUtils.getRequestParamMap(request);
        try {
            //验证签证
            boolean b = AlipaySignature.rsaCheckV2(map, alipayConfig.public_key, "utf-8", alipayConfig.getSigntype());
            if (b){
               alipayService.validate(map);
            }else{
                throw new RuntimeException("无效的回调！");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/test")
    public JSONObject test(@RequestBody JSONObject jsonObject){
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name","huchao");
        jsonObject1.put("age",18);
        jsonObject1.put("favor",new String[]{"123","234"});
        jsonObject.put("test",jsonObject1);
        Object test = jsonObject.getJSONObject("test").get("namekkjk");
        System.out.println(test);
        return jsonObject;
    }
}