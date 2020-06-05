package com.pd.rabbitmq.basic;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-06-02 13:44
 */
@Configuration
public class RabbitConfig {
    /*@Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUri("amqp://guest:guest@10.0.12.74:5672");
        return factory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin( ConnectionFactory connectionFactory){
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        admin.setAutoStartup(true);
        return admin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate( ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }*/
    

    @Bean("topicExchange")
    public TopicExchange topicExchange(){
        return new TopicExchange("PANDA_TOPIC_EXCHANGE");
    }

    @Bean("directExchange")
    public DirectExchange directExchange(){
        return new DirectExchange("PANDA_DIRECT_EXCHANGE");
    }

    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("PANDA_FANOUT_EXCHANGE");
    }


    // 三个队列
    @Bean("firstQueue")
    public Queue getFirstQueue(){
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-message-ttl",6000);
        return new Queue("PANDA_FIRST_QUEUE", false, false, true, args);
    }

    @Bean("secondQueue")
    public Queue getSecondQueue(){
        return new Queue("PANDA_SECOND_QUEUE");
    }

    @Bean("thirdQueue")
    public Queue getThirdQueue(){
        return new Queue("PANDA_THIRD_QUEUE");
    }

    @Bean
    public Binding bindFirst(@Qualifier("firstQueue")Queue queue,@Qualifier("directExchange")DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("panda.direct");
    }

    @Bean
    public Binding bindSecond(@Qualifier("secondQueue")Queue queue,@Qualifier("topicExchange")TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("#.panda.*");
    }

    @Bean
    public Binding bindThird(@Qualifier("thirdQueue")Queue queue,@Qualifier("fanoutExchange")FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }


}
