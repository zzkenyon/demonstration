package com.pd.rabbitmq;

import com.pd.rabbitmq.basic.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class RabbitmqApplicationTests {

    @Autowired
    RabbitTemplate template;
    @Test
    void contextLoads() {
        template.convertAndSend("PANDA_DIRECT_EXCHANGE","panda.direct",new User("zzk","zjhz",23));
        template.convertAndSend("PANDA_TOPIC_EXCHANGE","KKK.panda.direct",new User("zzk","zjhz",23));
    }

}
