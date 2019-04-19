package cn.javaexception.service.impl;

import cn.javaexception.pay.AlipayConfig;
import cn.javaexception.service.AlipayService;
import cn.javaexception.vo.AlipayQueryResponseVo;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import order_module.service.AlipayInterface;
import order_module.service.OrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.JsonData;

import java.util.Map;
import java.util.UUID;

@Service
public class AlipayServiceImpl implements AlipayService {
    @Autowired
    AlipayClient alipayClient;
    @Autowired
    AlipayConfig alipayConfig;
    @Reference
    OrderInterface orderInterface;
    @Reference
    AlipayInterface alipayInterface;
    @Override
    public String getTradeStatusByTradeNo(String tradeNo){
        AlipayTradeQueryResponse response=null;
        String status = null;
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setTradeNo(tradeNo);
        request.setBizModel(model);
        try {
            response = alipayClient.execute(request);
            System.out.println(response.getBody());
            AlipayQueryResponseVo vo = JSONObject.toJavaObject(JSONObject.parseObject(response.getBody()), AlipayQueryResponseVo.class);
            status  = vo.getAlipay_trade_query_response().getTrade_status();

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public String pay(String orderId) throws AlipayApiException {
        //查询商品
        JsonData jsonData = orderInterface.findOrderById(orderId);
        AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
        // 封装请求支付信息
        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
        //设置订单id
        model.setOutTradeNo(UUID.randomUUID().toString());
        //设置名字
        model.setSubject("test");
        //设置价格
        model.setTotalAmount("50000");
        //设置订单详情内容（可省略）
        model.setBody("sdsas");
        //设置延时
        model.setTimeoutExpress("2m");
        //设置支付方式
        model.setProductCode("QUICK_WAP_WAY");
        alipay_request.setBizModel(model);
        alipay_request.setNotifyUrl(alipayConfig.notify_url);
        alipay_request.setReturnUrl(alipayConfig.return_url);
        return alipayClient.pageExecute(alipay_request).getBody();
    }

    @Override
    public void validate(Map map) {
        //验证业务逻辑
        //验 证out_trade_no是否为商户系统中创建的订单号

        //查询该订单
        String out_trade_no = map.get("out_trade_no").toString();
        orderInterface.findOrderById(out_trade_no);
        //查看该订单支付状态
        String trade_status = getTradeStatusByTradeNo("out_trade_no");
        if (trade_status.equals("TRADE_SUCCESS")||trade_status.equals("TRADE_FINISHED")){
            //判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）
            float amount = Float.parseFloat(map.get("total_amount").toString());
            //TODO
            //如果一致则修改订单状态
            orderInterface.changeOrderStatus("1",2);

        }else{
            throw new RuntimeException("无效的回调");
        }

    }
}
