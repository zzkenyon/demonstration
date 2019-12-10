package com.pd.kafka.client;

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
public class KConsumer {
    public KafkaConsumer<String, String> getConsmer() {
        Properties props = new Properties();
        //设置kafka服务器
        props.put("bootstrap.servers", "192.168.2.112:9092,192.168.2.113:9092,192.168.2.114:9092");
        //消费者群组ID，发布-订阅模式，即如果一个生产者，多个消费者都要消费，那么需要定义自己的群组，同一个群组内的消费者只有一个能消费到消息
        props.put("group.id", "test");
        //true，消费者的偏移量将在后台定期提交；false关闭自动提交位移，在消息被完整处理之后再手动提交位移
        props.put("enable.auto.commit", "true");
        //如何设置为自动提交（enable.auto.commit=true）,这里设置自动提交周期
        props.put("auto.commit.interval.ms", "1000");
        //session.timeout.ms:在使用kafka的组管理时，用于检测消费者故障的超时
        props.put("session.timeout.ms", "30000");
        //key.serializer和value.serializer示例：将用户提供的key和value对象ProducerRecord转换成字节，你可以使用附带的ByteArraySerizlizaer或StringSerializer处理简单的byte和String类型.
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        return consumer;
    }



    public static void main(String[] args) {
        KConsumer kconsumer =  new KConsumer();
        KafkaConsumer<String, String> consumer = kconsumer.getConsmer();

        consumer.subscribe(Arrays.asList("test-topic"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records)
                System.out.println("offset =  "+record.offset()+", key = "+record.key()+", value = "+ record.value());
        }
    }
}
