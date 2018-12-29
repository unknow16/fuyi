package com.fuyi.upms.alone.auth;

public interface AuthService {
    String login(String username, String password);
    String refresh(String oldToken);
}