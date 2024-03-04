package com.atguigu.gulimall.product;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
* mybatis-plus逻辑删除
* 1. 配置全局的逻辑删除规则
* 2. 配置逻辑删除的组件Bean
* 3.给Bean配置逻辑删除的字段 对应建表时的字段
*
*  在Javabean中 添加 校验注解
* */
@SpringBootApplication
@MapperScan("com.atguigu.gulimall.product.dao")
@EnableDiscoveryClient
public class GulimallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
