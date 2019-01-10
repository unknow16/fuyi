package com.fuyi.upms.rpc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.fuyi.upms.rpc.mapper")
@SpringBootApplication(scanBasePackages = "com.fuyi")
public class UpmsServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UpmsServiceApplication.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .run(args);
    }
}
