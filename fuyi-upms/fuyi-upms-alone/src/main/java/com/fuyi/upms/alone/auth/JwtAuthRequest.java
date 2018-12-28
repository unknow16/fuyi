package com.fuyi.upms.alone.auth;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
public class JwtAuthRequest implements Serializable {

    private String username;
    private String password;

    public JwtAuthRequest() {
        super();
    }

    public JwtAuthRequest(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
