package cn.javaexception.pay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlipayConfiguration {

    @Bean
    public AlipayClient alipayClient(AlipayProperties alipayProperties){
        return new DefaultAlipayClient(alipayProperties.getGatewayUrl(),
                alipayProperties.getAppid(),
                alipayProperties.getAppPrivateKey(),
                alipayProperties.getFormate(),
                alipayProperties.getCharset(),
                alipayProperties.getAlipayPublicKey(),
                alipayProperties.getSignType());
    }
}