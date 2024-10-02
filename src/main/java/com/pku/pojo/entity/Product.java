package com.pku.pojo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Long productId;
    private String productName;
    private Double price;
    private Long stock;
}
