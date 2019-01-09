package com.fuyi.upms.rpc.api;

import com.fuyi.common.base.BaseService;
import com.fuyi.upms.rpc.entity.UpmsRole;
import com.fuyi.upms.rpc.entity.UpmsRoleExample;

public interface IUpmsRoleService extends BaseService<UpmsRole, UpmsRoleExample> {

    int updateRolePermissin(Integer roleId, Integer[] ids);
}
