package cn.javaexception.pay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlipayClientConfig {
    @Bean
    public AlipayClient getAlipayClient(AlipayConfig alipayConfig) {
        return new DefaultAlipayClient(alipayConfig.url,
                                    alipayConfig.appid,
                                    alipayConfig.private_key,
                                    alipayConfig.format,
                                    alipayConfig.charset,
                                    alipayConfig.public_key,
                                    alipayConfig.signtype);
    }
}
