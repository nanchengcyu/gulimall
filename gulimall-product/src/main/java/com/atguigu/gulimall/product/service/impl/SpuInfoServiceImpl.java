package com.atguigu.gulimall.product.service.impl;

import com.atguigu.common.constant.ProductConstant;
import com.atguigu.common.to.SkuHasStockVo;
import com.atguigu.common.to.es.SkuEsModel;
import com.atguigu.common.utils.R;
import com.atguigu.gulimall.product.entity.*;
import com.atguigu.gulimall.product.feign.SearchFeignService;
import com.atguigu.gulimall.product.feign.WareFeignService;
import com.atguigu.gulimall.product.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.SpuInfoDao;


@Service("spuInfoService")
@Slf4j
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {
    @Autowired
    SkuInfoService skuInfoService;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductAttrValueService attrValueService;
//    @Autowired
//    WareFeignService wareFeignService;
//    @Autowired
//    SearchFeignService searchFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }


//    public void up(Long spuId) {
//        //组装需要的数据 es中存储的实体字段
//        //1.查出当前spuid对于的所有的sku信息 品牌名字
//        List<SkuInfoEntity> skus = skuInfoService.getSkusBySpuId(spuId);
//        List<Long> skuIdList = skus.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());
//        //查询当前sku的所有可以呗检索的规格属性
//        List<ProductAttrValueEntity> baseAttrs = attrValueService.baseAttrlistforspu(spuId);
//        List<Long> attrIds = baseAttrs.stream().map(attr -> attr.getAttrId()).collect(Collectors.toList());
//
//        List<Long> searchAttrIds = attrValueService.selectSearchAttrIds(attrIds);
//
//        HashSet<Long> idSet = new HashSet<>(searchAttrIds);
//        List<SkuEsModel.Attrs> attrsList = baseAttrs.stream().filter(item -> {
//            return idSet.contains(item.getAttrId());
//        }).map(item -> {
//            SkuEsModel.Attrs attrs1 = new SkuEsModel.Attrs();
//            BeanUtils.copyProperties(item, attrs1);
//            return attrs1;
//        }).collect(Collectors.toList());
//        Map<Long, Boolean> stockMap = null;
//        try {
//            //调用feign远程
//            R<List<SkuHasStockVo>> skusHasStock = wareFeignService.getSkusHasStock(skuIdList);
//            stockMap = skusHasStock.getData().stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, item -> item.getHasStock()));
//        } catch (Exception e) {
//            log.error("库存服务查询异常，原因{}", e);
//        }
//
//
//        //2.封装每个sku的信息
//        Map<Long, Boolean> finalStockMap = stockMap;
//        List<SkuEsModel> upProducts = skus.stream().map(sku -> {
//            //组装需要的数据
//            SkuEsModel esModel = new SkuEsModel();
//
//            BeanUtils.copyProperties(sku, esModel);
//            esModel.setSkuPrice(sku.getPrice());
//            //todo 热度评分 0 后期优化
//            esModel.setHotScore(0L);
//            //库存 远程es
//            if (finalStockMap == null){
//                esModel.setHasStock(true);
//            }else {
//                esModel.setHasStock(finalStockMap.get(sku.getSkuId()));
//
//            }
//
//            esModel.setSkuImg(sku.getSkuDefaultImg());
//            BrandEntity brand = brandService.getById(esModel.getBrandId());
//            esModel.setBrandName(brand.getName());
//            esModel.setBrandImg(brand.getLogo());
//            CategoryEntity category = categoryService.getById(esModel.getCatalogId());
//
//            esModel.setCatalogName(category.getName());
//            //设置检索属性
//            esModel.setAttrs(attrsList);
//
//            return esModel;
//        }).collect(Collectors.toList());
//        //远程es
//        R r = searchFeignService.productStatusUp(upProducts);
//        if (r.getCode() == 0){
//        baseMapper.updateSpuStatus(spuId, ProductConstant.ProductStatusEnum.SPU_UP.getCode());
//        }
//        else {
//
//        }
//    }

}