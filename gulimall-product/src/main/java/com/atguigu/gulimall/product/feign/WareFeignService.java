package com.atguigu.gulimall.product.feign;

import com.atguigu.common.to.SkuHasStockVo;
import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * ClassName: WareFeginService
 * Package: com.atguigu.gulimall.product.feign
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/13 22:29
 * @Version 1.0
 */
@FeignClient("gulimall-ware")
public interface WareFeignService {
    @PostMapping("ware/waresku/hasstock")
    R<List<SkuHasStockVo>>  getSkusHasStock(@RequestBody List<Long> skuIds);
}
