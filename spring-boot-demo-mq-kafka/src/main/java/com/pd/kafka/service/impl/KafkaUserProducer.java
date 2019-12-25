package com.pd.kafka.service.impl;

import com.alibaba.fastjson.JSON;
import com.pd.kafka.bean.User;
import com.pd.kafka.service.MqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-12-25 10:58
 */
@Service
@Slf4j
public class KafkaUserProducer implements MqProducer<User> {
    private KafkaTemplate<String,String> kafkaTemplate;
    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String,String> template){
        this.kafkaTemplate = template;
    }

    @Override
    public void send(String topic, String key, User value) {
        String jsonStr = JSON.toJSONString(value);
        kafkaTemplate.send(topic,key,jsonStr);
    }

    @Override
    public void send(String topic, Map<String, User> messages) {

    }
}
