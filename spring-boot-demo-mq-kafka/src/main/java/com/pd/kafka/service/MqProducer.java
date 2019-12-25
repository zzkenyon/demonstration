package com.pd.kafka.service;

import java.util.Map;

public interface MqProducer<V> {
    /**
     * 向指定的topic发送单条消息
     * @param topic
     * @param key
     * @param value
     */
    void send(String topic,String key,V value);

    /**
     * 向指定的topic批量发送消息
     * @param topic
     * @param messages
     */
    void send(String topic, Map<String,V> messages);
}
