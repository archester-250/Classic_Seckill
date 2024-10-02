package com.pku.controller.admin;

import com.pku.pojo.dto.ProductDTO;
import com.pku.pojo.entity.Product;
import com.pku.result.Result;
import com.pku.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public Result<String> add(@RequestBody ProductDTO productDTO) {
        log.info("添加商品：{}", productDTO);
        productService.add(productDTO);
        return Result.success();
    }

    @PutMapping
    public Result<String> update(@RequestBody Product product) {
        log.info("修改商品：{}", product);
        productService.update(product);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable long id) {
        log.info("删除商品：{}", id);
        productService.delete(id);
        return Result.success();
    }
}
