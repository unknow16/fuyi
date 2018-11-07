package com.fuyi.upms.rpc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan("com.fuyi.upms.dao.mapper")
public class UpmsServiceBootApplication {
    public static void main(String[] args) {

        new SpringApplicationBuilder(UpmsServiceBootApplication.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .run(args);

    }
}
