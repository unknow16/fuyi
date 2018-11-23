package com.fuyi.upms.alone.service;

import com.fuyi.upms.dao.entity.UpmsRole;

import java.util.List;

/**
 * @ClassName PermissionService
 * @Description TODO
 * @Author fuyi
 * @Date 2018/11/23 15:06
 * @Version 1.0
 */
public interface PermissionService {

    Object selectPermissionByUserRoleId(List<UpmsRole> roles);
}
