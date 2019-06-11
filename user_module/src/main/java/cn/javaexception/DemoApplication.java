package cn.javaexception;


import cn.javaexception.config.AlipayConfig;
import cn.javaexception.config.BaiduConfig;
import cn.javaexception.util.AvoidDuplicateIntercepter;
import cn.javaexception.websocket.MyWebSocketHandler;
import cn.javaexception.websocket.WebSocketServer;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.baidu.aip.speech.AipSpeech;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.Map;

@SpringBootApplication
@EnableRedisRepositories
@EnableWebSocket
public class DemoApplication implements WebMvcConfigurer, WebSocketConfigurer {
    @Autowired
    AvoidDuplicateIntercepter avoidDuplicateIntercepter;
    @Autowired
    MyWebSocketHandler myWebSocketHandler;
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        //解决WebSocket不能注入的问题
        WebSocketServer.setApplicationContext(configurableApplicationContext);
    }

    @Bean
    @ConditionalOnWebApplication
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(avoidDuplicateIntercepter).addPathPatterns("/**").excludePathPatterns("/api/user/login");
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler,"/websocket/{userId}").addInterceptors(new HandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
                System.out.println(SecurityUtils.getSubject().getPrincipal());
                return true;
            }

            @Override
            public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
                System.out.println("after");
            }
        }).setAllowedOrigins("*");
    }
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
    @Bean
    public AipSpeech getAipSpeech(BaiduConfig baiduConfig){
        return new AipSpeech(baiduConfig.getAppid(),baiduConfig.getApikey(),baiduConfig.getSecret_key());
    }
}
