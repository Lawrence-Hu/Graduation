package cn.javaexception;

import org.springframework.stereotype.Component;

@Component
public class Jerry implements Person {
    @Override
    public void eat() {
        System.out.println("jerry eat");
    }
}
