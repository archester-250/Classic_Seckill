package com.pku.mq;

import com.alibaba.fastjson2.JSON;
import com.pku.config.RabbitMQConfiguration;
import com.pku.constant.MessageConstant;
import com.pku.exception.ProductIsEmptyException;
import com.pku.exception.SeckillHasEndedException;
import com.pku.exception.UserHasSeckillException;
import com.pku.pojo.entity.SeckillOrder;
import com.pku.pojo.entity.SeckillProduct;
import com.pku.service.SeckillOrderService;
import com.pku.service.SeckillProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class MQReceiver {
    @Autowired
    private SeckillProductService seckillProductService;
    @Autowired
    private SeckillOrderService seckillOrderService;
    @RabbitListener(queues = RabbitMQConfiguration.SECKILL_QUEUE)
    public synchronized void receive(String msg) {
        log.info("Receive message: {}", msg);
        SeckillMessage seckillMessage = JSON.parseObject(msg, SeckillMessage.class);
        Long userId = seckillMessage.getUserId();
        Long productId = seckillMessage.getProductId();
        SeckillProduct seckillProduct = seckillProductService.getSeckillProduct(productId, LocalDateTime.now());
        if(seckillProduct == null) {
            throw new SeckillHasEndedException(MessageConstant.ACTIVITY_HAS_ENDED);
        }
        if(seckillProduct.getStockCount() < 0){
            throw new ProductIsEmptyException(MessageConstant.USER_HAS_SECKILL);
        }
        SeckillOrder seckillOrder = seckillOrderService.getSeckillOrder(userId, productId);
        if(seckillOrder != null) {
            throw new UserHasSeckillException(MessageConstant.USER_HAS_SECKILL);
        }
        seckillOrderService.insertAndDescStock(userId, productId);
    }
}
