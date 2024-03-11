package com.atguigu.gulimall.product.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName: MybatisConfig
 * Package: com.atguigu.gulimall.product.config
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/11 16:10
 * @Version 1.0
 */

@Configuration
@EnableTransactionManagement
@MapperScan("com.atguigu.gulimall.product.dao")
public class MybatisPlusConfig {
    /**
     * 添加分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        paginationInterceptor.setOverflow(true);
        //最大单页数据
        paginationInterceptor.setLimit(1000);

        return paginationInterceptor;
    }
}
