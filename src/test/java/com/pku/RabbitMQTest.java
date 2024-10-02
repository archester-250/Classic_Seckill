package com.pku;


import com.pku.mq.MQSender;
import com.pku.mq.SeckillMessage;
import com.pku.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.test.context.SpringRabbitTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitMQTest {
    @Autowired
    private MQSender sender;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Test
    public void test() {
        sender.sendSeckillMessage(new SeckillMessage(1L, (long)1));
    }
}
