package cn.javaexception.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hcuhao
 * @date 2019-03-05-17:29
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 系统异常处理，比如：404,500
     *
     * @return JsonData
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData defaultErrorHandler(HttpServletRequest req, Exception e){
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return JsonData.buildError(404, e.getMessage());
        }
        if (e instanceof org.apache.shiro.authz.UnauthorizedException){
            return JsonData.buildError(401,"您的权限不足");
        }
        if(e instanceof  org.apache.shiro.authz.UnauthenticatedException){
            return JsonData.buildError(201, "登录身份失效,请登录!");
        }
        if(e instanceof org.springframework.http.converter.HttpMessageNotReadableException){
            return JsonData.buildError("请求参数错误!");
        }
        else {
            e.printStackTrace();
            return JsonData.buildError(500, "服务器忙,请稍后重试");
        }
    }
}