package com.pd.rabbitmq.basic;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/3 10:55
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof RabbitTemplate){
            ((RabbitTemplate) bean).setMessageConverter(new Jackson2JsonMessageConverter());
        }
        if(bean instanceof SimpleRabbitListenerContainerFactory){
            ( (SimpleRabbitListenerContainerFactory)bean).setMessageConverter(new Jackson2JsonMessageConverter());
        }
        return bean;
    }
}
