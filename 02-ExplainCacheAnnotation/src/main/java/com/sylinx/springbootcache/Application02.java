package com.sylinx.springbootcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Application02 {
    public static void main(String[] args) {
        SpringApplication.run(Application02.class, args);
    }
}