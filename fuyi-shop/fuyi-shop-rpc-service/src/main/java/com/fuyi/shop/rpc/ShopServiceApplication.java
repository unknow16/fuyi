package com.fuyi.shop.rpc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @ClassName ShopServiceApplication
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/4 16:13
 * @Version 1.0
 */
@MapperScan("com.fuyi.shop.rpc.mapper")
@SpringBootApplication(scanBasePackages = "com.fuyi.shop.rpc.service")
@ComponentScan("com.fuyi.shop.rpc.service")
public class ShopServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ShopServiceApplication.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .run(args);
        //SpringApplication.run(ShopServiceApplication.class, args);
    }
}

