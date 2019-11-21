package com.pd.returnformart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
/**
 * produces 属性指定响应的 Content-Type
 */
@RequestMapping(produces = "application/json")
public class SpringBootDemoReturnformatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoReturnformatApplication.class, args);
    }

    @GetMapping(value = "/")
    public String sayHello(){
        return "Hello,world!";
    }

    @GetMapping(value = "/user")
    public User getUser(){
        return new User("zhaozhengkang",29);
    }
}
