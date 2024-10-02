package com.pku.mq;

import com.alibaba.fastjson2.JSON;
import com.pku.config.RabbitMQConfiguration;
import com.pku.config.RedisConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQSender {
    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendSeckillMessage(SeckillMessage seckillMessage) {
        log.info("send seckillMessage:{}", JSON.toJSONString(seckillMessage));
        amqpTemplate.convertAndSend(RabbitMQConfiguration.SECKILL_QUEUE, JSON.toJSONString(seckillMessage));
    }
}
