package com.sylinx.springbootcache.customer;

import com.sylinx.springbootcache.entity.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

import java.util.ArrayList;
import java.util.Collection;

public class MultipleCacheResolver implements CacheResolver {

    private final CacheManager multiOperationCacheManager;

    private final CacheManager personCacheManager;

    public MultipleCacheResolver(CacheManager multiOperationCacheManager, CacheManager personCacheManager) {
        this.multiOperationCacheManager = multiOperationCacheManager;
        this.personCacheManager = personCacheManager;
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Collection<Cache> caches = new ArrayList<Cache>();
        Person person = (Person)context.getArgs()[0];
        if(person.getId() <= 5) {
            caches.add(multiOperationCacheManager.getCache("multiple1"));
        } else {
            caches.add(multiOperationCacheManager.getCache("multiple2"));
            caches.add(personCacheManager.getCache("persons"));
        }
        return caches;
    }
}
