package cn.javaexception.service;

import com.alipay.api.AlipayApiException;
import utils.JsonData;

public interface AlipayService {
    String pay(String orderId) throws AlipayApiException;
}
