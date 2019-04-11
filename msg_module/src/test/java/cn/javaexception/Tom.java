package cn.javaexception;

import org.springframework.stereotype.Component;

@Component
public class Tom implements Person {
    @Override
    public void eat() {
        System.out.println("tom eat");
    }
}
