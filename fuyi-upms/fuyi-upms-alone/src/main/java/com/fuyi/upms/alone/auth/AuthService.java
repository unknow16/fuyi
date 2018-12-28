package com.fuyi.upms.alone.auth;

import com.fuyi.upms.dao.entity.UpmsUser;

public interface AuthService {
    String login(String username, String password);
    String refresh(String oldToken);
}