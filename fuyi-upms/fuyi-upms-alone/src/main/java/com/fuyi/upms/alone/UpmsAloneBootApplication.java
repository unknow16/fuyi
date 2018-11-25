package com.fuyi.upms.alone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.fuyi.**")
@MapperScan("com.fuyi.upms.dao.mapper")
public class UpmsAloneBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(UpmsAloneBootApplication.class, args);
    }
}
