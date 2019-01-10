package com.fuyi.upms.rpc.api;

import com.fuyi.common.base.BaseService;
import com.fuyi.upms.rpc.entity.UpmsOrganization;
import com.fuyi.upms.rpc.entity.UpmsRole;
import com.fuyi.upms.rpc.entity.UpmsUser;
import com.fuyi.upms.rpc.entity.UpmsUserExample;

import java.util.List;

public interface IUpmsUserService extends BaseService<UpmsUser, UpmsUserExample> {

    UpmsUser selectUserByUsername(String username);
    /**
     * 新增用户
     * @param upmsUser
     * @return
     */
    UpmsUser createUser(UpmsUser upmsUser);

    /**
     * 查询用户角色
     * @param userId 用户id
     * @return
     */
    List<UpmsRole> selectUserRoles(int userId);

    /**
     * 查询用户组织
     * @param userId 用户id
     * @return
     */
    List<UpmsOrganization> selectUserOrganizations(int userId);

    /**
     * 分配角色
     * @param userId 用户id
     * @param roleIds 角色id列表
     * @return
     */
    int assignRoles(Integer userId, Integer[] roleIds);

}
