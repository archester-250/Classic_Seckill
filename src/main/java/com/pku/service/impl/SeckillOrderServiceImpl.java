package com.pku.service.impl;

import com.pku.context.BaseContext;
import com.pku.mapper.OrderMapper;
import com.pku.mapper.ProductMapper;
import com.pku.mapper.SeckillOrderMapper;
import com.pku.mapper.SeckillProductMapper;
import com.pku.pojo.entity.Order;
import com.pku.pojo.entity.Product;
import com.pku.pojo.entity.SeckillOrder;
import com.pku.pojo.entity.SeckillProduct;
import com.pku.service.SeckillOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.time.LocalDateTime;

@Service
@Slf4j
public class SeckillOrderServiceImpl implements SeckillOrderService {
    @Autowired
    private SeckillOrderMapper seckillOrderMapper;
    @Autowired
    private SeckillProductMapper seckillProductMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public SeckillOrder getSeckillOrder(Long userId, Long productId) {
        return seckillOrderMapper.getSeckillOrderByUserIdAndProductId(userId, productId);
    }

    @Transactional
    @Override
    public void insertAndDescStock(Long userId, Long productId) {
        log.info("开始插入，userId:{},productId:{}", userId, productId);
        SeckillProduct seckillProduct = seckillProductMapper.getSeckillProductByProductId(productId, null);
        SeckillProduct newSeckillProduct = SeckillProduct.builder()
                .stockCount(seckillProduct.getStockCount() - 1)
                .seckillId(seckillProduct.getSeckillId()).build();
        seckillProductMapper.update(newSeckillProduct);
        Product product = productMapper.selectByProductId(productId);
        Product newProduct = Product.builder()
                .productId(productId)
                .stock(product.getStock() - 1).build();
        productMapper.update(newProduct);

        Order order = Order.builder()
                .orderStatus(Order.COMPLETED)
                .userId(userId)
                .productId(productId)
                .createTime(LocalDateTime.now()).build();
        orderMapper.insert(order);
        SeckillOrder seckillOrder = SeckillOrder.builder()
                .orderId(order.getOrderId())
                .productId(productId)
                .userId(userId).build();
        seckillOrderMapper.insertSeckillOrder(seckillOrder);
    }
}
