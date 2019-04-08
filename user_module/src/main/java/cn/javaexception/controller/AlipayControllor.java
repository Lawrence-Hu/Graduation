package cn.javaexception.controller;

import cn.javaexception.pay.AlipayConfig;
import cn.javaexception.service.AlipayService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/alipay")
public class AlipayControllor {
//    @Autowired
//    private AlipayService alipayService;
    @GetMapping("/order")
    public void getPay(HttpServletResponse response){
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setSubject("test");
        model.setTotalAmount("15.22");
        model.setBody("sdsas");
        model.setTimeoutExpress("2m");
        model.setProductCode("QUICK_WAP_WAY ");
        alipay_request.setBizModel(model);
        // 设置异步通知地址
        alipay_request.setNotifyUrl(AlipayConfig.notify_url);
        // 设置同步地址
        alipay_request.setReturnUrl(AlipayConfig.return_url);

        // form表单生产
        String form = "";
        try {
            // 调用SDK生成表单
            form = client.pageExecute(alipay_request).getBody();
            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            try {
                response.getWriter().write(form);//直接将完整的表单html输出到页面
                System.out.println(form);
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/refund")
    public void refund(){
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
        AlipayFundTransToaccountTransferRequest alipayFundTransToaccountTransferRequest = new AlipayFundTransToaccountTransferRequest();
        AlipayFundTransToaccountTransferModel alipayFundTransToaccountTransferModel = new AlipayFundTransToaccountTransferModel();
        alipayFundTransToaccountTransferModel.setAmount("5000");
        alipayFundTransToaccountTransferModel.setOutBizNo("4562898655656565");
        alipayFundTransToaccountTransferModel.setPayeeType("ALIPAY_LOGONID");
        alipayFundTransToaccountTransferModel.setPayeeAccount("tukheo4380@sandbox.com");
        try {
            AlipayFundTransToaccountTransferResponse response = client.execute(alipayFundTransToaccountTransferRequest);
            String body = response.getBody();
            System.out.println(response.getOrderId()+"\n"+response.getPayDate()+"\n"+response.getOutBizNo());
            System.out.println(body);
            if(response.isSuccess()){
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}