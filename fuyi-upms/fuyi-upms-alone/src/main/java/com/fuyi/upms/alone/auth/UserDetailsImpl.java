package com.fuyi.upms.alone.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fuyi.upms.dao.entity.UpmsRole;
import com.fuyi.upms.dao.entity.UpmsUser;
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
public class UserDetailsImpl extends UpmsUser implements UserDetails {

    protected UserDetailsImpl(UpmsUser upmsUser) {
        super(upmsUser.getUserId(), upmsUser.getUsername(), upmsUser.getPassword(),
                upmsUser.getSalt(), upmsUser.getRealname(), upmsUser.getAvatar(),
                upmsUser.getPhone(), upmsUser.getEmail(), upmsUser.getSex(),
                upmsUser.getLocked(), upmsUser.getCtime(), upmsUser.getRoles());
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UpmsRole role : super.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return super.getLocked() == 0;
    }
}
