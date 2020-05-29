package com.pd.kafka.client;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-12-2 20:39
 */
public class KConsumer extends Thread{
    public KafkaConsumer<String, String> getConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.2.112:9092,192.168.2.113:9092,192.168.2.114:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_1");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        return consumer;
    }

    @Override
    public void run() {
        KafkaConsumer<String, String> consumer = getConsumer();

        consumer.subscribe(Arrays.asList("test-topic"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records)
                System.out.println("offset =  "+record.offset()+", key = "+record.key()+", value = "+ record.value());
        }
    }

    public static void main(String[] args) {
        new KConsumer().start();
    }
}
