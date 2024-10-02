package com.pku.pojo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductsPageDTO {
    private int pageSize;
    private int currentPage;
}
