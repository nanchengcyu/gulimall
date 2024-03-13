package com.atguigu.gulimall.ware.entity.vo;

import lombok.Data;

/**
 * ClassName: SkuHasStockVo
 * Package: com.atguigu.gulimall.ware.entity.vo
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/13 22:11
 * @Version 1.0
 */
@Data
public class SkuHasStockVo {
    private Long skuId;
    private Boolean hasStock;
}
