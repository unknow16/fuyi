package com.fuyi.upms.rpc.api;

import com.fuyi.common.base.BaseService;
import com.fuyi.upms.rpc.entity.UpmsPermission;
import com.fuyi.upms.rpc.entity.UpmsPermissionExample;
import com.fuyi.upms.rpc.entity.UpmsRole;

import java.util.List;

public interface IUpmsPermissionService extends BaseService<UpmsPermission, UpmsPermissionExample> {

    /**
     * 根据用户角色列表获取其权限
     * @param roles
     * @return
     */
    public abstract Object selectPermissionByUserRoleId(List<UpmsRole> roles);

    /**
     * 获取权限树数据
     * @return
     */
    public abstract Object selectPermissionTree();

    /**
     * 获取该角色关联的权限
     * @param roleId 角色id
     * @return
     */
    public abstract Object selectPermissionListByRoleId(int roleId);
}
