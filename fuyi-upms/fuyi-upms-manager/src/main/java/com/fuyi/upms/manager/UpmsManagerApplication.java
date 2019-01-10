package com.fuyi.upms.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.fuyi.upms.manager", exclude = {DataSourceAutoConfiguration.class} )
public class UpmsManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UpmsManagerApplication.class, args);
    }
}
