package com.pd.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootDemoActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoActuatorApplication.class, args);
    }

    @GetMapping(value = "/")
    public String sayHello(){
        try {
            Thread.sleep(1234);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello world!";
    }
}
