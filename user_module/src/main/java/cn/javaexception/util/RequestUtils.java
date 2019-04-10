package cn.javaexception.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestUtils {

    public static Map getRequestParamMap(HttpServletRequest request)
    {
        Map map = new HashMap();
        //得到枚举类型的参数名称，参数名称若有重复的只能得到第一个
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements())
        {
            String paramName = (String) enums.nextElement();
            String paramValue = request.getParameter(paramName);
            //形成键值对应的map
            map.put(paramName, paramValue);
        }
        return map;
    }
}
