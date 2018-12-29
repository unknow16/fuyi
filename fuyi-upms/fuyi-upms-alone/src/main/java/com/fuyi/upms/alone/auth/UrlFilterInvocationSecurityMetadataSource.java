package com.fuyi.upms.alone.auth;

import com.fuyi.upms.dao.entity.*;
import com.fuyi.upms.dao.mapper.UpmsPermissionMapper;
import com.fuyi.upms.dao.mapper.UpmsRoleMapper;
import com.fuyi.upms.dao.mapper.UpmsRolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 获取访问当前请求需要的角色（可能有多个），传递到AccessDecisionManager，在AccessDecisionManager中与当前登陆用户拥有的角色比较
 */
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private UpmsPermissionMapper upmsPermissionMapper;

    @Autowired
    private UpmsRolePermissionMapper upmsRolePermissionMapper;

    @Autowired
    private UpmsRoleMapper upmsRoleMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
//        if (antPathMatcher.match("/auth/**", requestUrl)) {
//
//        }

        List<UpmsPermission> upmsPermissions = upmsPermissionMapper.selectByExample(new UpmsPermissionExample());
        for(UpmsPermission upmsPermission : upmsPermissions) {
            if (antPathMatcher.match(upmsPermission.getUri(), requestUrl)) {
                UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
                upmsRolePermissionExample.createCriteria().andPermissionIdEqualTo(upmsPermission.getPermissionId());
                List<UpmsRolePermission> upmsRolePermissions = upmsRolePermissionMapper.selectByExample(upmsRolePermissionExample);

                String[] roleNames = new String[upmsRolePermissions.size()];
                for (int i = 0; i < upmsRolePermissions.size(); i++) {
                    UpmsRole upmsRole = upmsRoleMapper.selectByPrimaryKey(upmsRolePermissions.get(i).getRoleId());
                    roleNames[i] = upmsRole.getName();
                }
                return SecurityConfig.createList(roleNames);
            }
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
