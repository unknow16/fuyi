package com.fuyi.upms.manager.auth;

public interface IAuthService {
    String login(String username, String password);
    String refresh(String oldToken);
}