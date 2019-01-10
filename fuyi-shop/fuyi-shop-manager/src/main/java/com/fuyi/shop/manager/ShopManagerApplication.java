package com.fuyi.shop.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @ClassName ShopManagerApplication
 * @Description 电商后台管理系统启动类
 * @Author fuyi
 * @Date 2018/12/17 16:48
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = "com.fuyi.shop.manager", exclude = {DataSourceAutoConfiguration.class} )
public class ShopManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopManagerApplication.class, args);
    }
}
