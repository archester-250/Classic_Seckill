package com.pku.pojo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeckillOrder {
    private Long seckillId;
    private Long userId;
    private Long orderId;
    private Long productId;
}
