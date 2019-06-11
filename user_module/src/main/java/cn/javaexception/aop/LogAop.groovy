package cn.javaexception.aop

import cn.javaexception.annotation.OperateAnnotation
import cn.javaexception.entity.OperateLog
import cn.javaexception.entity.User
import cn.javaexception.mapper.OperateLogMapper
import com.alibaba.fastjson.JSON
import org.apache.shiro.SecurityUtils
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Aspect
@Component
class LogAop{
    @Autowired
    private ApplicationContext applicationContext
    @Autowired
    private OperateLogMapper mapper
    def before
    def after
    @Pointcut(value = "execution(public * cn.javaexception.controller.*.*(..))")
    void pointCut(){}

    @Before("pointCut()")
    void doBefore(JoinPoint joinPoint) throws IllegalAccessException, InstantiationException {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature()
        OperateAnnotation annotation = methodSignature.getMethod().getAnnotation(OperateAnnotation.class)
        if (annotation!=null){
            def service = annotation.service()
            def targetParam = annotation.params()
            Object bean = applicationContext.getBean(service)
            def params = joinPoint.getArgs()
            for (param in params){
                if(param.getClass()==targetParam){
                    println param
                    before = bean.invokeMethod(annotation.value(),param)
                }
            }
        }

    }
    @After("pointCut()")
    void doAfter(JoinPoint joinPoint) throws IOException {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature()
        OperateAnnotation annotation = methodSignature.getMethod().getAnnotation(OperateAnnotation.class)
        if (annotation!=null){
            Class<?> service = annotation.service()
            def targetParam = annotation.params()
            def category = annotation.category()
            Object bean = applicationContext.getBean(service)
            def params = joinPoint.getArgs()
            for (param in params){
                if(param.getClass()==targetParam){
                    after = bean.invokeMethod(annotation.value(),param)
                    if (after != before){
                        OperateLog log = new OperateLog()
                        def principal = (User)SecurityUtils.getSubject().getPrincipal()
                        log.setOpreaterId(principal.getId())
                        log.setCreatedTime(new Date())
                        log.setOperateCategoryId(category)
                        log.setBeforeOperateData(JSON.toJSONString(before))
                        log.setAfterOperateData(JSON.toJSONString(after))
                        mapper.insert(log)
                    }
                }
            }
        }
    }
}
