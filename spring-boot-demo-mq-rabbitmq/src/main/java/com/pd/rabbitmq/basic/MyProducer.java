package com.pd.rabbitmq.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.Serializable;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-06-02 15:31
 */
public class MyProducer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyProducer.class);
        RabbitTemplate template = context.getBean(RabbitTemplate.class);
        template.convertAndSend("PANDA_DIRECT_EXCHANGE","panda.direct","test msg");
        template.convertAndSend("PANDA_TOPIC_EXCHANGE","panda.topic","topic test msg...");

    }


}
