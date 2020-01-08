package com.pd.helloworld.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/8 15:42
 */
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("这是从自定义的ApplicationListener发来的消息");
    }
}
