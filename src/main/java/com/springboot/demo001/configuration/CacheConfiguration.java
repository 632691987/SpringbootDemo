package com.springboot.demo001.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

@EnableCaching
@Configuration
public class CacheConfiguration {

    /**
     *
     * 1, cacheManager 先获取相对应的缓存组件, 按照 cacheNames 获取，如果没有 cache 组建会自动创建
     * 2, 按照组件的 key 去获取，默认是 申明方法里面的 key, 或者默认是方法的参数
     * 3, 将结果放回到混存中
     *
     * 核心是 ConcurrentMapCacheManager, ConcurrentMapCache, 和 SimpleKeyGenerator
     *
     *
     *
     */

    @Bean("SysKeyGenerator")
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> method.getName() + "[" + Arrays.asList(params).toString() + "]";
    }
}
