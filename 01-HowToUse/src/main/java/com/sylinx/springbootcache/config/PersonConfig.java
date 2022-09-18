package com.sylinx.springbootcache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean
    public CacheManager inMemoryCache() {
        return new ConcurrentMapCacheManager("persons");
    }
}
