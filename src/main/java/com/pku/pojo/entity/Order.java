package com.pku.pojo.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Order {
    /**
     * 订单状态 1已完成 2已取消
     */
    public static final Integer COMPLETED = 1;
    public static final Integer CANCELLED = 2;
    private Long orderId;
    private Long userId;
    private Long productId;
    private int orderStatus;
    private LocalDateTime createTime;
}
