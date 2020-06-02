package com.pd.rabbitmq.basic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-06-02 15:37
 */
@Component
@RabbitListener(queues = "PANDA_FIRST_QUEUE")
public class MyConsumer_1 {
    @RabbitHandler
    public void onMessage(@Payload User msg){
        System.out.println(msg.toString());
    }

}
