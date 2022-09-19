package com.sylinx.springbootcache.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sylinx.springbootcache.service.PersonService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PersonService personService;

    private ApplicationContext applicationContext;

    @PostMapping("/showPersonCache")
    public String showPersonCache() throws JsonProcessingException, IllegalAccessException {
        CacheManager cacheManager = this.applicationContext.getBean("personCacheManager", CacheManager.class);

        ConcurrentMapCache persons = (ConcurrentMapCache)cacheManager.getCache("persons");
        return getCachedValue(persons);
    }

    @PostMapping("/showMultiCache")
    public String showMultiCache() throws JsonProcessingException, IllegalAccessException {
        CacheManager cacheManager = this.applicationContext.getBean("multiOperationCacheManager", CacheManager.class);
        ConcurrentMapCache multiple1 = (ConcurrentMapCache)cacheManager.getCache("multiple1");
        ConcurrentMapCache multiple2 = (ConcurrentMapCache)cacheManager.getCache("multiple2");
        return "multiple1 : " + getCachedValue(multiple1) + " \r\nmultiple2 : " + getCachedValue(multiple2);
    }

    private String getCachedValue(ConcurrentMapCache concurrentMapCache) throws JsonProcessingException, IllegalAccessException {
        Field field = ReflectionUtils.findField(ConcurrentMapCache.class, "store");
        ReflectionUtils.makeAccessible(field);
        ConcurrentMap<Object, Object> store = (ConcurrentMap<Object, Object>) field.get(concurrentMapCache);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.writeValueAsString(store);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
