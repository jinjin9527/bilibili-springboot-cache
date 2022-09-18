package com.sylinx.springbootcache.config;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AopConfig {

    @Value("${pointcutParam.system}")
    private String pointcutSystem;

    @Bean
    public NameMatchCacheOperationSource nameMatchCacheOperationSource(){
        NameMatchCacheOperationSource nameMatchCacheOperationSource = new NameMatchCacheOperationSource();
        CacheableOperation.Builder cacheableBuilder = new CacheableOperation.Builder();
        cacheableBuilder.setCacheName("system");
        cacheableBuilder.setKeyGenerator("myKeyGenerator");
        cacheableBuilder.setCacheManager("cacheManager");
        CacheableOperation cacheableOperation = cacheableBuilder.build();

        CachePutOperation.Builder cachePutBuilder = new CachePutOperation.Builder();
        cachePutBuilder.setCacheName("system");
        cachePutBuilder.setKeyGenerator("myKeyGenerator");
        cachePutBuilder.setCacheManager("cacheManager");
        CachePutOperation cachePutOperation = cachePutBuilder.build();

        CacheEvictOperation.Builder cacheEvictBuilder = new CacheEvictOperation.Builder();
        cacheEvictBuilder.setCacheName("system");
        cacheEvictBuilder.setKeyGenerator("myKeyGenerator");
        cacheEvictBuilder.setCacheManager("cacheManager");
        CacheEvictOperation cacheEvictOperation = cacheEvictBuilder.build();
        nameMatchCacheOperationSource.addCacheMethod("find*", List.of(cacheableOperation));
        nameMatchCacheOperationSource.addCacheMethod("update*", List.of(cachePutOperation));
        nameMatchCacheOperationSource.addCacheMethod("delete*", List.of(cacheEvictOperation));
        return nameMatchCacheOperationSource;
    }

    @Bean
    public DefaultPointcutAdvisor cacheAdvisor(KeyGenerator keyGenerator, DefaultListableBeanFactory beanFactory, CacheManager cacheManager, NameMatchCacheOperationSource nameMatchCacheOperationSource){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        if(pointcutSystem.equals("person")) {
            pointcut.setExpression("execution(* com.sylinx.springbootcache.person.repository.PersonRepository.*(..))");
        } else {
            pointcut.setExpression("execution(* com.sylinx.springbootcache.order.repository.OrderRepository.*(..))");
        }

        CacheInterceptor cacheInterceptor = new CacheInterceptor();
        cacheInterceptor.setCacheManager(cacheManager);
        cacheInterceptor.setCacheOperationSource(nameMatchCacheOperationSource);
        cacheInterceptor.setBeanFactory(beanFactory);
        cacheInterceptor.setKeyGenerator(keyGenerator);
        cacheInterceptor.afterSingletonsInstantiated();
        return  new DefaultPointcutAdvisor(pointcut, cacheInterceptor);
    }
}
