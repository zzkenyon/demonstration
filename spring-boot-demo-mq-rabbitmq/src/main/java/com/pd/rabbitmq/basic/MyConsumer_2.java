package com.pd.rabbitmq.basic;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-06-02 16:01
 */
@Component
@RabbitListener(queues = "PANDA_SECOND_QUEUE")
public class MyConsumer_2 {
    @RabbitHandler()
    public void onMessage(@Payload User msg){
        System.out.println(msg.toString());
    }

}
