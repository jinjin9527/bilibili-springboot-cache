package com.sylinx.springbootcache.customer;

import com.sylinx.springbootcache.person.entity.Person;


import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.cache.interceptor.KeyGenerator;


import java.lang.reflect.Method;

public class MyKeyGenerator implements KeyGenerator {
    @SneakyThrows
    public Object generate(Object target, Method method, Object... params) {

        Object id = params[0];
        if(!(params[0] instanceof Integer)) {
            id = PropertyUtils.getProperty(params[0], "id");
        }
        return target.getClass().getSimpleName() + "_"
                + id;
    }
}
