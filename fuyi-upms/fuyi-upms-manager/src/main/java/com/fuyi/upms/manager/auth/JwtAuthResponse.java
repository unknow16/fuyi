package com.fuyi.upms.manager.auth;

import java.io.Serializable;

public class JwtAuthResponse implements Serializable {

    private final String token;

    public JwtAuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
