package com.pku.controller.user;

import com.pku.pojo.entity.SeckillOrder;
import com.pku.result.Result;
import com.pku.service.SeckillOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user/seckillOrder")
public class SeckillOrderController {
    @Autowired
    private SeckillOrderService seckillOrderService;
    @GetMapping("/order/{userId}/{productId}")
    public Result<SeckillOrder> getSeckillOrder(@PathVariable Long userId, @PathVariable Long productId) {
        SeckillOrder seckillOrder = seckillOrderService.getSeckillOrder(userId, productId);
        return Result.success(seckillOrder);
    }
}
