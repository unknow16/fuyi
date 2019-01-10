package com.fuyi.upms.manager.auth;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuyi.upms.rpc.api.IUpmsUserService;
import com.fuyi.upms.rpc.entity.UpmsRole;
import com.fuyi.upms.rpc.entity.UpmsUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * spring security 中从db获取userDetail的Service
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference
    private IUpmsUserService upmsUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UpmsUser upmsUser = upmsUserService.selectUserByUsername(username);

        if (upmsUser != null) {
            List<UpmsRole> upmsRoles = upmsUserService.selectUserRoles(upmsUser.getUserId());
            upmsUser.setRoles(upmsRoles);
            return new UserDetailsImpl(upmsUser);
        } else {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
    }
}
