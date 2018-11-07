package com.fuyi.upms.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuyi.upms.dao.entity.TestUser;
import com.fuyi.upms.dao.mapper.TestUserMapper;
import com.fuyi.upms.rpc.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DefaultDemoService implements DemoService {

    @Autowired
    private TestUserMapper testUserMapper;

    public String sayHello(String name) {
        return "Hello, " + name + " ( from UpmsServiceBootApplication )";
    }

    public TestUser getUserById(Integer id) {
        TestUser testUser = testUserMapper.selectByPrimaryKey(id);
        return testUser;
    }
}
