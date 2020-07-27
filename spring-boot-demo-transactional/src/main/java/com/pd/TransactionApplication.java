package com.pd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/22 08:55
 */
@SpringBootApplication
public class TransactionApplication {
    public static void main(String[] args) {
        AnnotationConfigServletWebServerApplicationContext context =  (AnnotationConfigServletWebServerApplicationContext)SpringApplication.run(TransactionApplication.class,args);
        System.out.println(context.getBean(PlatformTransactionManager.class));
    }
}
