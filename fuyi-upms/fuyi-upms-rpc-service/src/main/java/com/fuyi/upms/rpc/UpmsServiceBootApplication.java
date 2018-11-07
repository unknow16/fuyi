package com.fuyi.upms.rpc;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class UpmsServiceBootApplication {
    public static void main(String[] args) {

        new SpringApplicationBuilder(UpmsServiceBootApplication.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .run(args);

    }
}
