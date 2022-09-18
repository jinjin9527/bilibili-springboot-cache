package com.sylinx.springbootcache.config;

import com.sylinx.springbootcache.customer.MyRedisCacheWriter;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.cache.transaction.TransactionAwareCacheManagerProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

//@Configuration
public class PersonConfig implements CachingConfigurer{

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        CacheManager cacheManager = RedisCacheManager.builder()
                .cacheWriter(new MyRedisCacheWriter(redisConnectionFactory))
                // 缓存与事务一致性 设定1
                .transactionAware()
                .withCacheConfiguration("persons", redisCacheConfiguration)
                .build();
        // 缓存与事务一致性 设定2
        return new TransactionAwareCacheManagerProxy(cacheManager);
    }


    @Bean
    public CacheErrorHandler errorHandler(){
        return new SimpleCacheErrorHandler(){
            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                exception.printStackTrace();
            }
        };
    }
}
