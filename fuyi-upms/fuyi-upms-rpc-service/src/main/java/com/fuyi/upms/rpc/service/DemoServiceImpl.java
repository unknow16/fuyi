package com.fuyi.upms.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuyi.upms.rpc.api.DemoService;

@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        return "Hello, " + name + " ( from UpmsServiceBootApplication )";
    }

}
