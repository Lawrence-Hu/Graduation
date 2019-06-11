package cn.javaexception.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "baidu")
@Component
@Data
public class BaiduConfig {
    private String appid;
    private String apikey;
    private String secret_key;
}
