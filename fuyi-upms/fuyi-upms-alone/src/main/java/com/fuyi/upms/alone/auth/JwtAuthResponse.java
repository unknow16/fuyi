package com.fuyi.upms.alone.auth;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
public class JwtAuthResponse implements Serializable {

    private final String token;

    public JwtAuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
