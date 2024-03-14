package com.atguigu.gulimall.search.service.Impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.common.to.es.SkuEsModel;
import com.atguigu.gulimall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.atguigu.gulimall.search.config.GulimallElasticSearchConfig.COMMON_OPTIONS;
import static com.atguigu.gulimall.search.constant.EsConstant.PRODUCT_INDEX;

/**
 * ClassName: ProductSaveServiceImpl
 * Package: com.atguigu.gulimall.search.service.Impl
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/14 10:07
 * @Version 1.0
 */
@Service
@Slf4j
public class ProductSaveServiceImpl implements ProductSaveService {
    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public Boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {
        //1.在es中创建索引,且在可视化界面建立好映射关系

        //2.保存前端传递过来的数据到es中
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel model : skuEsModels) {
            //给es中那个索引
            IndexRequest request = new IndexRequest(PRODUCT_INDEX);
            request.id(model.getSkuId().toString());
            String jsonString = JSON.toJSONString(model);
            request.source(jsonString, XContentType.JSON);
            bulkRequest.add(request);

        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, COMMON_OPTIONS);

        //todo 批量出错解决
        boolean b = bulk.hasFailures();
        List<String> collect = Arrays.stream(bulk.getItems()).map(item -> item.getId()).collect(Collectors.toList());
        log.error("商品上架错误：{}", collect);
        return b;


    }
}
