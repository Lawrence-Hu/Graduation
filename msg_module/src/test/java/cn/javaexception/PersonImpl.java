package cn.javaexception;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("person")
@Primary
public class PersonImpl implements Person {
    Person jerry =new Jerry();
    Person tom = new Tom();
    Person person;
    Random random = new Random();

    public PersonImpl() {
        System.out.println("init");
        if (random.nextBoolean()) {
            this.person = jerry;
        } else {
            this.person = tom;
        }
    }

    @Override
    public void eat() {
        this.person.eat();
    }
}
