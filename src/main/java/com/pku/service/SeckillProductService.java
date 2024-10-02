package com.pku.service;

import com.pku.pojo.dto.ProductsPageDTO;
import com.pku.pojo.entity.SeckillProduct;
import com.pku.result.PageResult;

import java.time.LocalDateTime;
import java.util.List;

public interface SeckillProductService {
    PageResult pageQuery(ProductsPageDTO productsPageDTO);

    SeckillProduct getSeckillProduct(Long productId, LocalDateTime localDateTime);

    List<SeckillProduct> list();
}
