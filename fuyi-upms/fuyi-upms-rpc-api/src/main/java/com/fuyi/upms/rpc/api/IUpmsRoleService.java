package com.fuyi.upms.rpc.api;

import com.fuyi.common.base.BaseService;
import com.fuyi.upms.rpc.entity.UpmsRole;
import com.fuyi.upms.rpc.entity.UpmsRoleExample;
import com.fuyi.upms.rpc.entity.UpmsRolePermission;

import java.util.List;

public interface IUpmsRoleService extends BaseService<UpmsRole, UpmsRoleExample> {

    int updateRolePermissin(Integer roleId, Integer[] ids);

    /**
     * 查询包含该权限的角色列表
     * @param permissionId 权限
     * @return 权限列表
     */
    List<UpmsRolePermission> selectRolesByPermissionId(Integer permissionId);
}
