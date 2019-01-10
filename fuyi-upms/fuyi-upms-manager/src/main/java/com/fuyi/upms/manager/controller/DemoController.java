package com.fuyi.upms.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuyi.common.base.BaseResult;
import com.fuyi.upms.rpc.api.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Reference
    private DemoService demoService;

    @RequestMapping("/demo")
    public BaseResult demo() {
        String data = demoService.sayHello("fuyi");
        return BaseResult.ok("demo测试成功！", data);
    }
}
