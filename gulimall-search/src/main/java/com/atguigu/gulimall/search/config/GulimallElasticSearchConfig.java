package com.atguigu.gulimall.search.config;

import lombok.Builder;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: GulimallElasticSearchConfig
 * Package: com.atguigu.gulimall.search.config
 * Description:
 *
 * @Author 南城余
 * @Create 2024/3/13 16:00
 * @Version 1.0
 */
@Configuration
public class GulimallElasticSearchConfig {
    public static final RequestOptions COMMON_OPTIONS;

    static {
    RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
    COMMON_OPTIONS = builder.build();
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder builder = null;
        builder = RestClient.builder(new HttpHost("47.113.148.239", 9200, "http"));
        return new RestHighLevelClient(builder);
    }

}
