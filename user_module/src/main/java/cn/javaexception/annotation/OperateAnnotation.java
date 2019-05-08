package cn.javaexception.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//代表Permission注解保留在的阶段
@Target(ElementType.METHOD)//标注在方法上面
public @interface OperateAnnotation{
    String value() default "operateLog";
    Class<?> service();
    Class<?> params();
    String category();
}
