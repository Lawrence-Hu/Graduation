package cn.javaexception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TestPerson {
    @Autowired
    Person person;
    void test(){
        person.eat();
    }
}
