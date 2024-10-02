package com.pku.pojo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private String productName;
    private double price;
    private long stock;
}
