package com.pd.helloworld.event;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/12/3 13:58
 */
public class Main {
    public static void main(String[] args){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        Person person = applicationContext.getBean(Person.class);
        person.setHungry(0);
        person.needEat();
    }
}
