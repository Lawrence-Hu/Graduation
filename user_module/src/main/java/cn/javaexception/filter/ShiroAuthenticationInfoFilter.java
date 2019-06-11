package cn.javaexception.filter;

import cn.javaexception.util.RequestUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.tomcat.util.http.RequestUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 重写shiro的UserFilter，实现通过OPTIONS请求
 * @author MDY
 */
public class ShiroAuthenticationInfoFilter extends FormAuthenticationFilter {

    /**
     * 在访问过来的时候检测是否为OPTIONS请求，如果是就直接返回true
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            RequestUtils.setHeader(httpRequest,httpResponse);
            return true;
        }
        return super.preHandle(request,response);
    }

    /**
     * 为response设置header，实现跨域
     */


}

