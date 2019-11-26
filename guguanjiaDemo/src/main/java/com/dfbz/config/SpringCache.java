package com.dfbz.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;

/**
 * @author Chen
 * @description
 * @date 2019/11/26 17
 */
@Configuration
@EnableCaching  //开启springcache的注解支持
public class SpringCache {
    @Bean
    public CacheManager getCacheManager(RedisTemplate<String, Object> template) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(template);//创建缓存管理对象

        //根据传入的缓存名字集合  创建并管理多个缓存对象

        ArrayList<String> cacheNames = new ArrayList<>();
        cacheNames.add("officeCache");
        redisCacheManager.setCacheNames(cacheNames);
        redisCacheManager.setDefaultExpiration(1800);
        return redisCacheManager;

    }
}
