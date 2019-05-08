package cn.javaexception.service;

import com.alipay.api.AlipayApiException;

import java.util.Map;

public interface AlipayService {
    String getTradeStatusByTradeNo(String tradeNo);

    String pay(String orderId) throws AlipayApiException;

    void validate(Map map);
}
