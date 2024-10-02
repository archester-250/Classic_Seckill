package com.pku.service;

import com.pku.pojo.entity.SeckillOrder;

public interface SeckillOrderService {
    SeckillOrder getSeckillOrder(Long userId, Long productId);

    void insertAndDescStock(Long userId, Long productId);
}
