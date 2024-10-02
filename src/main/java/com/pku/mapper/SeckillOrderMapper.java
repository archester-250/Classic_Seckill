package com.pku.mapper;

import com.pku.pojo.entity.SeckillOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SeckillOrderMapper {
    @Select("select * from seckill_orders where user_id = #{userId} and product_id = #{productId}")
    SeckillOrder getSeckillOrderByUserIdAndProductId(Long userId, Long productId);

    void insertSeckillOrder(SeckillOrder seckillOrder);
}
