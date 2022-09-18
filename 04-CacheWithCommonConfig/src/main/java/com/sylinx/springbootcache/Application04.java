package com.sylinx.springbootcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableCaching
public class Application04 {
    public static void main(String[] args) {
        SpringApplication.run(Application04.class, args);
    }
}
