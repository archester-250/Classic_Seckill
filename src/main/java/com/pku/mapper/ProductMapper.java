package com.pku.mapper;

import com.github.pagehelper.Page;
import com.pku.pojo.dto.ProductDTO;
import com.pku.pojo.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {
    @Select("select * from products")
    Page<Product> selectAll();

    @Select("select * from products where product_id = #{productId}")
    Product selectByProductId(Long productId);

    void insert(ProductDTO productDTO);

    void update(Product product);

    @Delete("delete from products where product_id = #{id}")
    void deleteByProductId(long id);
}
