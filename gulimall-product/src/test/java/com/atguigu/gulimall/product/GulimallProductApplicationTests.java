package com.atguigu.gulimall.product;

import com.atguigu.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Arrays;

@Slf4j
@SpringBootTest
class GulimallProductApplicationTests {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    RedissonClient redissonClient;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void redisTest(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("hello","ncy");

        //查询
        String hello = ops.get("hello");
        System.out.println("之前保存的数据是"+hello);
    }
    @Test
    void redissonTest(){
        System.out.println(redissonClient);

    }
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
