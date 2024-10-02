package com.pku.pojo.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SeckillProduct {
    private Long seckillId;
    private Long productId;
    private Double seckillPrice;
    private Long stockCount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
