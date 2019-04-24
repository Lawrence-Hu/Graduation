package cn.javaexception.shrio;

import cn.javaexception.filter.ShiroAuthenticationInfoFilter;
import cn.javaexception.filter.ShiroAuthorizationInfoFilter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
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
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("perms",new ShiroAuthorizationInfoFilter());
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/api/cart/*", "perms[user]");
        map.put("/api/deliverAddress/*", "perms[user]");
        map.put("/api/favourite/*", "perms[user]");
        map.put("/api/user/*", "perms[user]");
        map.put("/api/order/*", "perms[user]");
        map.put("/api/admin/super/*", "perms[superAdmin]");
        map.put("/api/admin/normal/*", "perms[normalAdmin]");
        map.put("/api/admin/verify/*", "perms[verifyAdmin]");
        map.put("/api/user/register", "anon");
        map.put("/api/local/toLogin", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/api/local/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/api/user/unAuth");
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
