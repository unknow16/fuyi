package com.fuyi.upms.alone.service;

import com.fuyi.framework.service.annotation.BaseServiceAnnotation;
import com.fuyi.framework.service.base.BaseServiceImpl;
import com.fuyi.upms.dao.entity.UpmsRole;
import com.fuyi.upms.dao.entity.UpmsRoleExample;
import com.fuyi.upms.dao.entity.UpmsRolePermission;
import com.fuyi.upms.dao.entity.UpmsRolePermissionExample;
import com.fuyi.upms.dao.mapper.UpmsRoleMapper;
import com.fuyi.upms.dao.mapper.UpmsRolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@BaseServiceAnnotation
public class RoleServiceImpl extends BaseServiceImpl<UpmsRoleMapper, UpmsRole, UpmsRoleExample> implements RoleService {

    @Autowired
    private UpmsRolePermissionMapper upmsRolePermissionMapper;

    @Override
    public int updateRolePermissin(Integer roleId, Integer[] ids) {
        UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
        upmsRolePermissionExample.createCriteria().andRoleIdEqualTo(roleId);

        upmsRolePermissionMapper.deleteByExample(upmsRolePermissionExample);

        int count = 0;
        for (int i = 0; i < ids.length; i++) {
            UpmsRolePermission upmsRolePermission = new UpmsRolePermission();
            upmsRolePermission.setRoleId(roleId);
            upmsRolePermission.setPermissionId(ids[i]);
            count += upmsRolePermissionMapper.insert(upmsRolePermission);
        }

        return count;
    }
}
