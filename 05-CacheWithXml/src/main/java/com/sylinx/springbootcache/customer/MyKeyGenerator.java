package com.sylinx.springbootcache.customer;

import com.sylinx.springbootcache.entity.Person;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class MyKeyGenerator implements KeyGenerator {
    public Object generate(Object target, Method method, Object... params) {
        Integer id = -1;
        if(params[0] instanceof Person) {
            id = ((Person) params[0]).getId();
        } else {
            id = (Integer) params[0];
        }
        return target.getClass().getSimpleName() + "_"
                + id;
    }
}
