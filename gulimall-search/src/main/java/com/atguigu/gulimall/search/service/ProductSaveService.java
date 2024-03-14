package com.atguigu.gulimall.search.service;

import com.atguigu.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * ClassName: ProductService
 * Package: com.atguigu.gulimall.search.service
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/14 10:04
 * @Version 1.0
 */
public interface ProductSaveService {


     Boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
