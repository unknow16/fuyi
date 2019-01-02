package com.fuyi.shop.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName ShopManagerBootApplication
 * @Description 电商后台管理系统启动类
 * @Author fuyi
 * @Date 2018/12/17 16:48
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan("com.fuyi.**")
@MapperScan("com.fuyi.shop.manager.mapper")
public class ShopManagerBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopManagerBootApplication.class, args);
    }
}
