package com.atguigu.gulimall.product;

import com.atguigu.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@Slf4j
@SpringBootTest
class GulimallProductApplicationTests {
    @Autowired
    private CategoryService categoryService;
    @Test
    void findCateGoryLogPathTest(){
        Long[] catelogPath = categoryService.findCatelogPath(30L);
        log.info("分类路径：",catelogPath);
        log.info("分类路径：", Arrays.asList(catelogPath));

    }
    @Test
    void contextLoads() {
    }

}
