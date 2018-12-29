package com.fuyi.upms.alone.auth;

import com.fuyi.framework.web.base.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登陆认证控制器
 * Created by fuyi on 2018/12/28 0018.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${jwt.header}")
    private String header;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/getToken", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthRequest request) throws AuthenticationException {
        final String token = authService.login(request.getUsername(), request.getPassword());

        return ResponseEntity.ok(BaseResult.ok(new JwtAuthResponse(token)));
    }

    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(this.header);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthResponse(refreshedToken));
        }
    }
}
