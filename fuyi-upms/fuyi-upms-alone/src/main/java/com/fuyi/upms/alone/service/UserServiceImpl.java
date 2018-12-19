package com.fuyi.upms.alone.service;

import com.fuyi.framework.service.annotation.BaseServiceAnnotation;
import com.fuyi.framework.service.base.BaseServiceImpl;
import com.fuyi.upms.dao.entity.*;
import com.fuyi.upms.dao.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@BaseServiceAnnotation
public class UserServiceImpl extends BaseServiceImpl<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UserService {

    @Autowired
    private UpmsUserMapper upmsUserMapper;

    @Autowired
    private UpmsUserRoleMapper upmsUserRoleMapper;

    @Autowired
    private UpmsRoleMapper upmsRoleMapper;

    @Autowired
    private UpmsUserOrganizationMapper upmsUserOrganizationMapper;

    @Autowired
    private UpmsOrganizationMapper upmsOrganizationMapper;

    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria().andUsernameEqualTo(upmsUser.getUsername());
        List<UpmsUser> upmsUsers = upmsUserMapper.selectByExample(upmsUserExample);
        if (upmsUsers != null && upmsUsers.size() > 0) {
            return null;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        long time = System.currentTimeMillis();

        //String salt = UUID.randomUUID().toString().replaceAll("-", "");
        //upmsUser.setSalt(salt);
        //upmsUser.setPassword(MD5Util.md5(upmsUser.getPassword() + upmsUser.getSalt()));
        upmsUser.setPassword(encoder.encode(upmsUser.getPassword()));
        upmsUser.setCtime(time);
        upmsUserMapper.insertSelective(upmsUser);
        return upmsUser;
    }

    @Override
    public List<UpmsRole> selectUserRoles(int userId) {
        UpmsUserRoleExample upmsUserRoleExample = new UpmsUserRoleExample();
        upmsUserRoleExample.createCriteria().andUserIdEqualTo(userId);
        List<UpmsUserRole> upmsUserRoles = upmsUserRoleMapper.selectByExample(upmsUserRoleExample);

        List<UpmsRole> roles = new ArrayList<>(upmsUserRoles.size());
        for (UpmsUserRole upmsUserRole : upmsUserRoles) {
            UpmsRole upmsRole = upmsRoleMapper.selectByPrimaryKey(upmsUserRole.getRoleId());
            roles.add(upmsRole);
        }
        return roles;
    }

    @Override
    public List<UpmsOrganization> selectUserOrganizations(int userId) {
        UpmsUserOrganizationExample upmsUserOrganizationExample = new UpmsUserOrganizationExample();
        upmsUserOrganizationExample.createCriteria().andUserIdEqualTo(userId);
        List<UpmsUserOrganization> upmsUserOrganizations = upmsUserOrganizationMapper.selectByExample(upmsUserOrganizationExample);

        List<UpmsOrganization> organizations = new ArrayList<>(upmsUserOrganizations.size());
        for(UpmsUserOrganization userOrganization : upmsUserOrganizations) {
            UpmsOrganization upmsOrganization = upmsOrganizationMapper.selectByPrimaryKey(userOrganization.getOrganizationId());
            organizations.add(upmsOrganization);
        }
        return organizations;
    }

    @Override
    @Transactional
    public int assignRoles(Integer userId, Integer[] roleIds) {
        int count = 0;
        for (int i = 0; i < roleIds.length; i++) {
            UpmsUserRole upmsUserRole = new UpmsUserRole();
            upmsUserRole.setUserId(userId);
            upmsUserRole.setRoleId(roleIds[i]);
            count += upmsUserRoleMapper.insert(upmsUserRole);
        }
        return count;
    }
}
