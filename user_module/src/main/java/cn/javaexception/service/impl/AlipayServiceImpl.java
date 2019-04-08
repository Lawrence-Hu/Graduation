package cn.javaexception.service.impl;

import cn.javaexception.pay.AlipayConfig;
import cn.javaexception.pay.AlipayProperties;
import cn.javaexception.service.AlipayService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AlipayServiceImpl implements AlipayService {
//    @Autowired
//    AlipayClient alipayClient;
    @Autowired
    AlipayProperties alipayProperties;
    @Override
    public String pay(String orderId) throws AlipayApiException {
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
        // 封装请求支付信息
        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setSubject("test");
        model.setTotalAmount("12");
        model.setBody("test");
        model.setTimeoutExpress("2m");
        model.setProductCode("QUICK_WAP_WAY");
        alipay_request.setBizModel(model);
        // 设置异步通知地址
        // 设置同步地址
        alipay_request.setReturnUrl(alipayProperties.getReturnUrl());
        alipay_request.setNotifyUrl(alipayProperties.getNotifyUrl());
        String form ="";
        form = client.pageExecute(alipay_request).getBody();
    return form;
    }
}
