package com.pd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConsumerServiceTest {
    ConsumerService service;
    @Autowired
    public void setService(ConsumerService service) {
        this.service = service;
    }

    @Test
    public void tset(){
        service.sayHello();
    }

}