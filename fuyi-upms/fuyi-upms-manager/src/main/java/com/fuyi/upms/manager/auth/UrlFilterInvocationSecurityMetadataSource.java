package com.fuyi.upms.manager.auth;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuyi.upms.rpc.api.IUpmsPermissionService;
import com.fuyi.upms.rpc.api.IUpmsRoleService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;

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
            return SecurityConfig.createList("ROLE_ANONYMOUS");
        }

        String[] roles = upmsRoleService.selectRolesByRequestUri(requestUrl);
        if (roles != null && roles.length >= 1) {
            return SecurityConfig.createList(roles);
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
