package cn.javaexception.filter;

import cn.javaexception.annotation.AvoidDuplicate;
import cn.javaexception.util.JsonData;
import cn.javaexception.util.RequestUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AvoidDuplicateIntercepter implements HandlerInterceptor {
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestUtils.setHeader(request,response);
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            System.out.println("option");
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.getAnnotation(AvoidDuplicate.class)==null){
            return true;
        }
        String token = request.getHeader("FORM-TOKEN");
        //没有带token
        if(token == null ){
            response.getWriter().write(JSONObject.toJSONString(JsonData.buildError(201,"登录身份失效，请登录")));
            return false;
        }
        //有token
        String isSubmit = redisTemplate.opsForValue().get(token);
        //说明第一次提交
        if(isSubmit==null){
            response.setHeader("content-type","application/json;charset=utf-8");
            response.getWriter().write(JSONObject.toJSONString(JsonData.buildError(-1,"非法FORM-TOKEN！")));
            return false;
        }
        if(isSubmit.equals("handled")){
            redisTemplate.opsForValue().set(token,"Handling");
            Thread.sleep(5000);
            return true;
        } else {
            //提交表单
            response.setHeader("content-type","application/json;charset=utf-8");
            response.getWriter().write(JSONObject.toJSONString(JsonData.buildError(-1,"请勿重复提交表单！")));
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            System.out.println("option1111");
            return ;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.getAnnotation(AvoidDuplicate.class)!=null){
            String token = request.getHeader("FORM-TOKEN");
            redisTemplate.opsForValue().set(token,"handled");
        }
    }
}
