package com.fuyi.upms.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuyi.common.annotation.BaseServiceAnnotation;
import com.fuyi.common.base.BaseServiceImpl;
import com.fuyi.upms.rpc.api.IUpmsSystemService;
import com.fuyi.upms.rpc.entity.UpmsSystem;
import com.fuyi.upms.rpc.entity.UpmsSystemExample;
import com.fuyi.upms.rpc.mapper.UpmsSystemMapper;

@Service
@BaseServiceAnnotation
public class UpmsSystemServiceImpl extends BaseServiceImpl<UpmsSystemMapper, UpmsSystem, UpmsSystemExample> implements IUpmsSystemService {
}
