package com.fuyi.upms.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuyi.common.annotation.BaseServiceAnnotation;
import com.fuyi.common.base.BaseServiceImpl;
import com.fuyi.upms.rpc.api.IUpmsOrganizationService;
import com.fuyi.upms.rpc.entity.UpmsOrganization;
import com.fuyi.upms.rpc.entity.UpmsOrganizationExample;
import com.fuyi.upms.rpc.mapper.UpmsOrganizationMapper;

@Service
@BaseServiceAnnotation
public class UpmsOrganizationServiceImpl extends BaseServiceImpl<UpmsOrganizationMapper, UpmsOrganization, UpmsOrganizationExample> implements IUpmsOrganizationService {
}
