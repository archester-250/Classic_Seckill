package com.pku.mapper;

import com.github.pagehelper.Page;
import com.pku.pojo.entity.Product;
import com.pku.pojo.entity.SeckillProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SeckillProductMapper {
    @Select("select * from seckill_products")
    Page<SeckillProduct> page();

    SeckillProduct getSeckillProductByProductId(Long productId, LocalDateTime localDateTime);

    void update(SeckillProduct newProduct);

    @Select("select * from seckill_products")
    List<SeckillProduct> selectAll();
}
