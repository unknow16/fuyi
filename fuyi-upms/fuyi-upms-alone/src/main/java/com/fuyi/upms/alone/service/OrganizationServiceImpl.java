package com.fuyi.upms.alone.service;

import com.fuyi.framework.service.annotation.BaseServiceAnnotation;
import com.fuyi.framework.service.base.BaseServiceImpl;
import com.fuyi.upms.dao.entity.UpmsOrganization;
import com.fuyi.upms.dao.entity.UpmsOrganizationExample;
import com.fuyi.upms.dao.mapper.UpmsOrganizationMapper;
import org.springframework.stereotype.Service;

@Service
@BaseServiceAnnotation
public class OrganizationServiceImpl extends BaseServiceImpl<UpmsOrganizationMapper, UpmsOrganization, UpmsOrganizationExample> implements OrganizationService {
}
