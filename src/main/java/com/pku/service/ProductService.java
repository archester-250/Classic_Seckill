package com.pku.service;

import com.pku.pojo.dto.ProductDTO;
import com.pku.pojo.dto.ProductsPageDTO;
import com.pku.pojo.entity.Product;
import com.pku.result.PageResult;

public interface ProductService {
    PageResult pageQuery(ProductsPageDTO productsPageDTO);
    void add(ProductDTO productDTO);

    void update(Product product);

    void delete(long id);
}
