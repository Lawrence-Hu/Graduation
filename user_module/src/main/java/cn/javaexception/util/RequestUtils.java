package cn.javaexception.util;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestUtils {

    public static Map<String, String> getRequestParamMap(HttpServletRequest request)
    {
        Map<String, String> map = new HashMap<>();
        //得到枚举类型的参数名称，参数名称若有重复的只能得到第一个
        Enumeration<String> enums = request.getParameterNames();
        while (enums.hasMoreElements())
        {
            String paramName = enums.nextElement();
            String paramValue = request.getParameter(paramName);
            //形成键值对应的map
            map.put(paramName, paramValue);
        }
        return map;
    }
    public static void setHeader(HttpServletRequest request, HttpServletResponse response){
        //跨域的header设置
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", request.getMethod());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        //防止乱码，适用于传输JSON数据
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }
}
