package com.pd.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootDemoHelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoHelloworldApplication.class, args);
    }

    @GetMapping("/")
    public String hello(){
        return "Hello World!";
    }

    /**
     * @author zhaozhengkang
     * @description
     * @date 2019/12/9 14:10
     */
    @Bean
    public TestBean testBean(){
        return new TestBean("主类内部的test");
    }
}
