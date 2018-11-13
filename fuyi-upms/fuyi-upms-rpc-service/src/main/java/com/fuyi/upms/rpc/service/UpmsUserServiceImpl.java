package com.fuyi.upms.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuyi.framework.service.annotation.BaseServiceAnnotation;
import com.fuyi.framework.service.base.BaseServiceImpl;
import com.fuyi.upms.dao.entity.UpmsUser;
import com.fuyi.upms.dao.entity.UpmsUserExample;
import com.fuyi.upms.dao.mapper.UpmsUserMapper;
import com.fuyi.upms.rpc.api.UpmsUserService;

@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
@BaseServiceAnnotation
public class UpmsUserServiceImpl extends BaseServiceImpl<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        return null;
    }

}
