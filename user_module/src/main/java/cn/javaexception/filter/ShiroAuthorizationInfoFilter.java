package cn.javaexception.filter;

import cn.javaexception.util.RequestUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShiroAuthorizationInfoFilter extends PermissionsAuthorizationFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            RequestUtils.setHeader(httpRequest,httpResponse);
            return true;
        }
        return super.preHandle(request, response);
    }


}
