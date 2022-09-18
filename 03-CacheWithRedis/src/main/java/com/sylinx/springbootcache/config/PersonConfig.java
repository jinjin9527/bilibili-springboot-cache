package com.sylinx.springbootcache.config;

import com.sylinx.springbootcache.customer.MyLogicalService;
import com.sylinx.springbootcache.customer.MyRedisCacheWriter;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class PersonConfig {

    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory, MyLogicalService myLogicalService) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
//                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        CacheManager cacheManager = RedisCacheManager.builder()
                .withCacheConfiguration("persons", redisCacheConfiguration)
//                .withCacheConfiguration("orders", redisCacheConfiguration)
                .cacheWriter(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
//                .cacheWriter(myRedisCacheWriter(redisConnectionFactory, myLogicalService))
                .build();
        return cacheManager;
    }

    @Bean
    public RedisCacheWriter myRedisCacheWriter(RedisConnectionFactory redisConnectionFactory, MyLogicalService myLogicalService){
        return new MyRedisCacheWriter(redisConnectionFactory, myLogicalService);
    }
}
