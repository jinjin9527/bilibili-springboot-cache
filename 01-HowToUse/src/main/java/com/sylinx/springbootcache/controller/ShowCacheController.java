package com.sylinx.springbootcache.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.BeansException;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentMap;

@RestController
public class ShowCacheController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @PostMapping("/showCache")
    public String showCache() throws IllegalAccessException, JsonProcessingException {
        CacheManager cacheManager = this.applicationContext.getBean(CacheManager.class);
        ConcurrentMapCache persons = (ConcurrentMapCache)cacheManager.getCache("persons");
        Field field = ReflectionUtils.findField(ConcurrentMapCache.class, "store");
        ReflectionUtils.makeAccessible(field);
        ConcurrentMap<Object, Object> store = (ConcurrentMap<Object, Object>) field.get(persons);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.writeValueAsString(store);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
