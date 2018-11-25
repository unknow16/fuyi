package com.fuyi.upms.alone.service;

import com.fuyi.framework.service.annotation.BaseServiceAnnotation;
import com.fuyi.framework.service.base.BaseServiceImpl;
import com.fuyi.upms.dao.entity.UpmsRole;
import com.fuyi.upms.dao.entity.UpmsRoleExample;
import com.fuyi.upms.dao.mapper.UpmsRoleMapper;
import org.springframework.stereotype.Service;

@Service
@BaseServiceAnnotation
public class RoleServiceImpl extends BaseServiceImpl<UpmsRoleMapper, UpmsRole, UpmsRoleExample> implements RoleService {
}
