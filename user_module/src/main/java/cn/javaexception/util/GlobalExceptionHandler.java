package cn.javaexception.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.JsonData;

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
        e.printStackTrace();
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return JsonData.buildError(404, e.getMessage());
        } else {
            return JsonData.buildError(500, "服务器忙，登录失败");
        }
    }
}