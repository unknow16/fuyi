package com.fuyi.shop.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName ShopPortalBootApplication
 * @Description 电商系统前台系统启动类
 * @Author fuyi
 * @Date 2018/12/17 16:50
 * @Version 1.0
 */
@ComponentScan("com.fuyi.shop.service.controller")
@SpringBootApplication(scanBasePackages = "com.fuyi.shop.service.controller")
public class ShopPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopPortalApplication.class, args);
    }
}
