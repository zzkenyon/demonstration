package com.pd.kafka;

import com.pd.kafka.bean.User;
import com.pd.kafka.config.KafkaConstant;
import com.pd.kafka.service.impl.KafkaUserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@RestController
public class KafkaApplication {
    KafkaUserProducer producer;
    @Autowired
    public void setProducer(KafkaUserProducer producer){
        this.producer = producer;
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @GetMapping(value = "/send")
    public void sendMessage(){
        Random r = new Random();
        for(int i = 0; i < 1000; i++){
            User u = new User();
            u.setId(r.nextLong());
            u.setAge(r.nextInt());
            u.setName(UUID.randomUUID().toString());
            producer.send(KafkaConstant.TOPIC_TEST,"key_"+i,u);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
