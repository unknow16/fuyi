package com.fuyi.upms.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuyi.upms.rpc.api.DemoService;

@Service
class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        return "Hello, " + name + " ( from UpmsServiceBootApplication )";
    }

}
