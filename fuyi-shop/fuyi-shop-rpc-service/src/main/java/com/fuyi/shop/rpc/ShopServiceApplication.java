package com.fuyi.shop.rpc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@MapperScan("com.fuyi.shop.rpc.mapper")
@SpringBootApplication(scanBasePackages = "com.fuyi")
public class ShopServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ShopServiceApplication.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .run(args);
    }
}

