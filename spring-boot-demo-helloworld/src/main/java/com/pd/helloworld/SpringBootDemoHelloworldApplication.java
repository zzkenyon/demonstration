package com.pd.helloworld;

import com.pd.helloworld.context.MyBeanFactoryPostProcessor;
import com.pd.helloworld.context.MyContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootDemoHelloworldApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringBootDemoHelloworldApplication.class);
        springApplication.addInitializers(new MyContextInitializer());
        springApplication.run(args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name){
        return "Hello, "+name+ " !";
    }

}
