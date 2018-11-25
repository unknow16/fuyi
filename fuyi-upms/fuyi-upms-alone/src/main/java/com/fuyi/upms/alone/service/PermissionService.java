package com.fuyi.upms.alone.service;

import com.fuyi.framework.api.base.BaseService;
import com.fuyi.upms.dao.entity.UpmsPermission;
import com.fuyi.upms.dao.entity.UpmsPermissionExample;
import com.fuyi.upms.dao.entity.UpmsRole;

import java.util.List;

/**
 * @ClassName PermissionService
 * @Description TODO
 * @Author fuyi
 * @Date 2018/11/23 15:06
 * @Version 1.0
 */
public interface PermissionService extends BaseService<UpmsPermission, UpmsPermissionExample> {

    Object selectPermissionByUserRoleId(List<UpmsRole> roles);
}
