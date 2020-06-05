package com.pd.kafka.service.impl;

import com.alibaba.fastjson.JSON;
import com.pd.kafka.bean.User;
import com.pd.kafka.config.KafkaConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-12-25 11:21
 */
@Component
@Slf4j
public class KafkaUserConsumer {
    @KafkaListener(topics = KafkaConstant.TOPIC_TEST, containerFactory = "ackContainerFactory")
    public void handleMessage(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            Object message = record.value();
 //           User u = JSON.parseObject(message,User.class);
            log.info("收到消息: {}", message.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }
}
