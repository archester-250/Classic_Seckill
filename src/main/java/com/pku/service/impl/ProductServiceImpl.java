package com.pku.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pku.mapper.ProductMapper;
import com.pku.pojo.dto.ProductDTO;
import com.pku.pojo.dto.ProductsPageDTO;
import com.pku.pojo.dto.UserDTO;
import com.pku.pojo.entity.Product;
import com.pku.result.PageResult;
import com.pku.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public PageResult pageQuery(ProductsPageDTO productsPageDTO) {
        PageHelper.startPage(productsPageDTO.getCurrentPage(), productsPageDTO.getPageSize());
        Page<Product> productPage = productMapper.selectAll();
        return new PageResult(productPage.getTotal(), productPage.getResult());
    }

    @Override
    public void add(ProductDTO productDTO) {
        productMapper.insert(productDTO);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public void delete(long id) {
        productMapper.deleteByProductId(id);
    }
}
