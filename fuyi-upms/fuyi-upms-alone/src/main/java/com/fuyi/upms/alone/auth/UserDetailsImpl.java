package com.fuyi.upms.alone.auth;

import com.fuyi.upms.dao.entity.UpmsRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * spring security中定义的用户对象
 */
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private Byte locked;
    private List<UpmsRole> roleList;

    public UserDetailsImpl(String username, String password, Byte locked, List<UpmsRole> roleList) {
        this.username = username;
        this.password = password;
        this.locked = locked;
        this.roleList = roleList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UpmsRole role : this.roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.locked == 0 ? true : false;
    }
}
