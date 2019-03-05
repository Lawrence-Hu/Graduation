package cn.javaexception.shrio;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author hcuhao
 * @date 2019-03-03-10:57
 */
@Component
public class ShrioConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String,String> map = new LinkedHashMap<>();
        map.put("/cart/*","authc");
        map.put("local/toLogin","anon");
        map.put("/deliverAddress","authc");
        map.put("/favourite","authc");
        map.put("/order","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/local/toLogin");
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public UserRealm getUserRealm() {
        return new UserRealm();
    }
}
