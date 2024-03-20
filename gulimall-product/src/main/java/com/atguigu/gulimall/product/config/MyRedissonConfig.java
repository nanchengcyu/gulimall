package com.atguigu.gulimall.product.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * ClassName: MyRedissonConfig
 * Package: com.atguigu.gulimall.product.config
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/18 15:18
 * @Version 1.0
 */
@Configuration
public class MyRedissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        //1.创建配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://106.14.202.122:6379")
                .setPassword("1234");

        //2.根据Config创建出RedissonClient实例
        RedissonClient redissonClient = Redisson.create(config);


        return redissonClient;
    }
}
