package com.pku.controller.user;

import com.pku.pojo.dto.ProductsPageDTO;
import com.pku.result.PageResult;
import com.pku.result.Result;
import com.pku.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("UserProductController")
@Slf4j
@RequestMapping("/user/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public Result<PageResult> pageProducts(ProductsPageDTO productsPageDTO) {
        log.info("分页查询产品：{}", productsPageDTO);
        PageResult pageResult = productService.pageQuery(productsPageDTO);
        return Result.success(pageResult);
    }
}
