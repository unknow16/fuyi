package com.fuyi.upms.rpc.api;

import com.fuyi.upms.dao.entity.TestUser;

public interface DemoService {

    String sayHello(String name);

    TestUser getUserById(Integer id);
}
