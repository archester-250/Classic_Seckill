package com.pku.controller.user;

import com.pku.constant.KeyConstant;
import com.pku.constant.MessageConstant;
import com.pku.context.BaseContext;
import com.pku.mq.MQSender;
import com.pku.mq.SeckillMessage;
import com.pku.pojo.dto.ProductsPageDTO;
import com.pku.pojo.entity.SeckillProduct;
import com.pku.result.PageResult;
import com.pku.result.Result;
import com.pku.service.SeckillProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController("UserSeckillProductController")
@Slf4j
@RequestMapping("/user/seckillProduct")
public class SeckillProductController implements InitializingBean {
    @Autowired
    private SeckillProductService seckillProductService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private MQSender mqSender;
    private final HashMap<Long, Boolean> localCache = new HashMap<>();
    @Override
    public void afterPropertiesSet() throws Exception {
        List<SeckillProduct> seckillProducts = seckillProductService.list();
        for(SeckillProduct seckillProduct : seckillProducts){
            redisTemplate.opsForValue().set(KeyConstant.PRODUCT_STOCK_KEY_PREFIX + seckillProduct.getProductId(), seckillProduct.getStockCount());
            localCache.put(seckillProduct.getProductId(), false);
        }
    }

    @GetMapping("/seckillProducts")
    public Result<PageResult> pageProducts(ProductsPageDTO productsPageDTO) {
        log.info("分页查询秒杀产品：{}", productsPageDTO);
        PageResult pageResult = seckillProductService.pageQuery(productsPageDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/seckill/{productId}")
    public Result<String> seckill(@PathVariable Long productId) {
        if(localCache.get(productId)){
            return Result.error(MessageConstant.PRODUCT_IS_EMPTY);
        }
        if(redisTemplate.opsForValue().decrement(KeyConstant.PRODUCT_STOCK_KEY_PREFIX + productId) < 0){
            localCache.put(productId, true);
            return Result.error(MessageConstant.PRODUCT_IS_EMPTY);
        }
        SeckillMessage seckillMessage = new SeckillMessage(BaseContext.getCurrentId(), productId);
        mqSender.sendSeckillMessage(seckillMessage);
        return Result.success();
    }

    @GetMapping("/seckill/{productId}")
    public Result<SeckillProduct> getSeckillProduct(@PathVariable Long productId) {
        SeckillProduct seckillProduct = seckillProductService.getSeckillProduct(productId, LocalDateTime.now());
        return Result.success(seckillProduct);
    }
}
