package com.pd.helloworld.event;

import lombok.Data;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/12/3 13:50
 */
@Data
@Component
public class Person implements ApplicationEventPublisherAware {
    private int hungry ;
    private String name;
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void needEat(){
        if(this.hungry == 0){
            System.out.println("我饿了");
            new Thread(()->this.applicationEventPublisher.publishEvent(new HungryEvent(this))).start();
            System.out.println("已发出饿了事件");
        }
    }
}
