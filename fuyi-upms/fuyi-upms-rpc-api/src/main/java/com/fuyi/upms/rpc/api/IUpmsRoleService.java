package com.fuyi.upms.rpc.api;

import com.fuyi.common.base.BaseService;
import com.fuyi.upms.rpc.entity.UpmsRole;
import com.fuyi.upms.rpc.entity.UpmsRoleExample;
import com.fuyi.upms.rpc.entity.UpmsRolePermission;

import java.util.List;

public interface IUpmsRoleService extends BaseService<UpmsRole, UpmsRoleExample> {

    int updateRolePermissin(Integer roleId, Integer[] ids);

    /**
     * 查询访问该uri所需要的角色列表
     * @param targetRequestUri 请求目标url
     * @return 角色名列表
     */
    String[] selectRolesByRequestUri(String targetRequestUri);
}
