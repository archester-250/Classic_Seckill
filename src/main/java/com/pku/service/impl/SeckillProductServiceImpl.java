package com.pku.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pku.mapper.SeckillProductMapper;
import com.pku.pojo.dto.ProductsPageDTO;
import com.pku.pojo.entity.Product;
import com.pku.pojo.entity.SeckillProduct;
import com.pku.result.PageResult;
import com.pku.service.SeckillProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class SeckillProductServiceImpl implements SeckillProductService {
    @Autowired
    private SeckillProductMapper seckillProductMapper;
    @Override
    public PageResult pageQuery(ProductsPageDTO productsPageDTO) {
        PageHelper.startPage(productsPageDTO.getCurrentPage(), productsPageDTO.getPageSize());
        Page<SeckillProduct> productPage = seckillProductMapper.page();
        return new PageResult(productPage.getTotal(), productPage.getResult());
    }

    @Override
    public List<SeckillProduct> list() {
        return seckillProductMapper.selectAll();
    }

    @Override
    public SeckillProduct getSeckillProduct(Long productId, LocalDateTime localDateTime) {
        return seckillProductMapper.getSeckillProductByProductId(productId, localDateTime);
    }
}
