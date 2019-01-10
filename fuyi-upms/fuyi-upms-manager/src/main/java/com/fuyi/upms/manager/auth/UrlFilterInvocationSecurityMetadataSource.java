package com.fuyi.upms.manager.auth;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuyi.upms.rpc.api.IUpmsPermissionService;
import com.fuyi.upms.rpc.api.IUpmsRoleService;
import com.fuyi.upms.rpc.entity.UpmsPermission;
import com.fuyi.upms.rpc.entity.UpmsPermissionExample;
import com.fuyi.upms.rpc.entity.UpmsRole;
import com.fuyi.upms.rpc.entity.UpmsRolePermission;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 1. 获取访问当前请求需要的角色（可能有多个），传递到AccessDecisionManager，在AccessDecisionManager中与当前登陆用户拥有的角色比较
 */
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Reference
    private IUpmsPermissionService upmsPermissionService;

    @Reference
    private IUpmsRoleService upmsRoleService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        if (antPathMatcher.match("/auth/**", requestUrl)) {
            return SecurityConfig.createList("ROLE_LOGIN");
        }

        UpmsPermissionExample upmsPermissionExample = new UpmsPermissionExample();
        upmsPermissionExample.createCriteria().andStatusEqualTo((byte) 1);
        List<UpmsPermission> upmsPermissions = upmsPermissionService.selectByExample(upmsPermissionExample);
        for(UpmsPermission upmsPermission : upmsPermissions) {
            if (antPathMatcher.match(upmsPermission.getUri(), requestUrl)) {
                List<UpmsRolePermission> upmsRolePermissions = upmsRoleService.selectRolesByPermissionId(upmsPermission.getPermissionId());

                String[] roleNames = new String[upmsRolePermissions.size()];
                for (int i = 0; i < upmsRolePermissions.size(); i++) {
                    UpmsRole upmsRole = upmsRoleService.selectByPrimaryKey(upmsRolePermissions.get(i).getRoleId().longValue());
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
