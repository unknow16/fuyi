package com.fuyi.upms.alone.service;

import com.fuyi.framework.service.annotation.BaseServiceAnnotation;
import com.fuyi.framework.service.base.BaseServiceImpl;
import com.fuyi.upms.dao.entity.*;
import com.fuyi.upms.dao.mapper.UpmsRoleMapper;
import com.fuyi.upms.dao.mapper.UpmsRolePermissionMapper;
import com.fuyi.upms.dao.mapper.UpmsSystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@BaseServiceAnnotation
public class RoleServiceImpl extends BaseServiceImpl<UpmsRoleMapper, UpmsRole, UpmsRoleExample> implements RoleService {

    @Autowired
    private UpmsRolePermissionMapper upmsRolePermissionMapper;

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
}
