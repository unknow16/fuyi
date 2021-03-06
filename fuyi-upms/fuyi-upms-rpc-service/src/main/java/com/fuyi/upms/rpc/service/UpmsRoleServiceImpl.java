package com.fuyi.upms.rpc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuyi.common.annotation.BaseServiceAnnotation;
import com.fuyi.common.base.BaseServiceImpl;
import com.fuyi.upms.rpc.api.IUpmsRoleService;
import com.fuyi.upms.rpc.entity.*;
import com.fuyi.upms.rpc.mapper.UpmsPermissionMapper;
import com.fuyi.upms.rpc.mapper.UpmsRoleMapper;
import com.fuyi.upms.rpc.mapper.UpmsRolePermissionMapper;
import com.fuyi.upms.rpc.mapper.UpmsSystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@BaseServiceAnnotation
public class UpmsRoleServiceImpl extends BaseServiceImpl<UpmsRoleMapper, UpmsRole, UpmsRoleExample> implements IUpmsRoleService {

    @Autowired
    private UpmsPermissionMapper upmsPermissionMapper;

    @Autowired
    private UpmsRolePermissionMapper upmsRolePermissionMapper;

    @Autowired
    private UpmsRoleMapper upmsRoleMapper;

    @Autowired
    private UpmsSystemMapper upmsSystemMapper;

    @Override
    public int updateRolePermissin(Integer roleId, Integer[] ids) {
        UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
        upmsRolePermissionExample.createCriteria().andRoleIdEqualTo(roleId);

        upmsRolePermissionMapper.deleteByExample(upmsRolePermissionExample);

        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        upmsSystemExample.setOrderByClause("orders asc");
        List<UpmsSystem> upmsSystems = upmsSystemMapper.selectByExample(upmsSystemExample);

        Set<Integer> systemIdSet = new HashSet<>();
        Set<Integer> allIdSet = new HashSet<>();
        for(int i = 0; i < ids.length; i++) {
            allIdSet.add(ids[i]);
            for(UpmsSystem upmsSystem : upmsSystems) {
                if (upmsSystem.getSystemId().intValue() == ids[i].intValue()) {
                    systemIdSet.add(ids[i]);
                }
            }
        }

        allIdSet.removeAll(systemIdSet);


        int count = 0;
        for (Integer id : allIdSet) {
            UpmsRolePermission upmsRolePermission = new UpmsRolePermission();
            upmsRolePermission.setRoleId(roleId);
            upmsRolePermission.setPermissionId(id);
            count += upmsRolePermissionMapper.insert(upmsRolePermission);
        }

        return count;
    }

    @Override
    public String[] selectRolesByRequestUri(String targetRequestUri) {
        // 1. 查出所有权限
        UpmsPermissionExample upmsPermissionExample = new UpmsPermissionExample();
        upmsPermissionExample.createCriteria().andStatusEqualTo((byte) 1);
        List<UpmsPermission> upmsPermissions = upmsPermissionMapper.selectByExample(upmsPermissionExample);

        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for(UpmsPermission upmsPermission : upmsPermissions) {

            // 2. 去匹配目标uri
            if (antPathMatcher.match(upmsPermission.getUri(), targetRequestUri)) {
                UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
                upmsRolePermissionExample.createCriteria().andPermissionIdEqualTo(upmsPermission.getPermissionId());
                List<UpmsRolePermission> upmsRolePermissions = upmsRolePermissionMapper.selectByExample(upmsRolePermissionExample);

                String[] roleNames = new String[upmsRolePermissions.size()];
                for (int i = 0; i < upmsRolePermissions.size(); i++) {
                    UpmsRole upmsRole = upmsRoleMapper.selectByPrimaryKey(upmsRolePermissions.get(i).getRoleId());
                    roleNames[i] = upmsRole.getName();
                }
                return roleNames;
            }
        }
        return new String[0];
    }
}
