package com.sylinx.springbootcache.config;

import com.sylinx.springbootcache.customer.MultipleCacheResolver;
import com.sylinx.springbootcache.customer.MyKeyGenerator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PersonConfig {

    @Bean
    @Primary
    public CacheManager personCacheManager() {
        return new ConcurrentMapCacheManager("persons");
    }

    @Bean
    public CacheManager multiOperationCacheManager() {
        return new ConcurrentMapCacheManager("multiple1", "multiple2");
    }

    @Bean
    public KeyGenerator myKeyGenerator(){
        return new MyKeyGenerator();
    }

    @Bean
    public CacheResolver multiCacheResolver() {
        return new MultipleCacheResolver(multiOperationCacheManager(), personCacheManager());
    }
}
