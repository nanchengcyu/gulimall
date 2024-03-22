package com.atguigu.gulimall.product.feign;

import com.atguigu.common.to.product.SkuReductionTO;
import com.atguigu.common.to.product.SpuBoundTO;
import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: SpuFeignService
 * Package: com.atguigu.gulimall.product.feign
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/22 16:35
 * @Version 1.0
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTO spuBoundTO);
    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTO skuReductionTO);
}
