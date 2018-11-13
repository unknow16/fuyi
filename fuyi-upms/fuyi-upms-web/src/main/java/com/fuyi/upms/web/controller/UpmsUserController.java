package com.fuyi.upms.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuyi.upms.dao.entity.TestUser;
import com.fuyi.upms.dao.entity.UpmsUser;
import com.fuyi.upms.rpc.api.UpmsUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpmsUserController {

    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:12345")
    private UpmsUserService upmsUserService;

    @RequestMapping("/user/{id}")
    public UpmsUser getUserById(@PathVariable Integer id) {
        UpmsUser upmsUser = upmsUserService.selectByPrimaryKey(id);
        return upmsUser;
    }
}
