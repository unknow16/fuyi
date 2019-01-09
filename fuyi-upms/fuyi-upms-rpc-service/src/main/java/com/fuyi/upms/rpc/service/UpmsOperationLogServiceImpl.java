package com.fuyi.upms.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuyi.common.annotation.BaseServiceAnnotation;
import com.fuyi.common.base.BaseServiceImpl;
import com.fuyi.upms.rpc.api.IUpmsOperationLogService;
import com.fuyi.upms.rpc.entity.UpmsLog;
import com.fuyi.upms.rpc.entity.UpmsLogExample;
import com.fuyi.upms.rpc.mapper.UpmsLogMapper;

@Service
@BaseServiceAnnotation
public class UpmsOperationLogServiceImpl extends BaseServiceImpl<UpmsLogMapper, UpmsLog, UpmsLogExample> implements IUpmsOperationLogService {
}
