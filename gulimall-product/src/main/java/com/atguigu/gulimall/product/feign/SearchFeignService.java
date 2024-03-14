package com.atguigu.gulimall.product.feign;

import com.atguigu.common.to.es.SkuEsModel;
import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * ClassName: SearchFeignService
 * Package: com.atguigu.gulimall.product.feign
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/14 11:37
 * @Version 1.0
 */
@FeignClient("gulimall-search")
public interface SearchFeignService {
    //上架商品
    @PostMapping("/search/save/product")
     R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);

}
