package com.fuyi.upms.alone.auth;

import com.fuyi.upms.dao.entity.UpmsUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库查出user
        //ArrayList<String> roles = new ArrayList<String>();
        //roles.add("ROLE_ADMIN");
        //roles.add("ROLE_USER");
        //User user = new User("123", "1234", "$2a$10$YmgSNdSh0EOEz8eAivAEJ.Sasci4kds147wB0yxA6bHWXJnDxRAKe", 1, "haha", roles);

        UpmsUser user = null;

        if (user != null) {
            return new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getLocked(), user.getRoles());
        } else {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
    }
}
