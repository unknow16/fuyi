package com.fuyi.upms.manager.auth;

import com.alibaba.fastjson.JSONObject;
import com.fuyi.common.base.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 用户登陆认证控制器
 * Created by fuyi on 2018/12/28 0018.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 根据用户名密码生成jwt的token返回
     */
    @RequestMapping(value = "/getToken", method = RequestMethod.POST)
    public BaseResult createAuthToken(@RequestBody JwtRequestVo jwtRequestVo) throws AuthenticationException {

        //securityConfig中配置的auth允许，所以不会被UsernamePasswordAuthenticationFilter拦截，在此颁发token前，自己执行认证
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(jwtRequestVo.getUsername(), jwtRequestVo.getPassword());

        // authenticationManager实现类ProviderManager中List<AuthenticationProvider>只包含一个DaoAuthenticationProvider，
        // 该provider调用UserDetailsService的loadUserByUsername()比对认证
        // 返回填充角色权限的Authentication(其是UsernamePasswordAuthenticationToken类型)
        final Authentication authenticate = authenticationManager.authenticate(token);

        // 设置进SecurityContextHolder中的安全上下文中
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        //根据查到的信息生成token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequestVo.getUsername());
        final String jwtToken = jwtTokenUtil.generateToken(userDetails);

        JSONObject tokenJson = new JSONObject();
        tokenJson.put("token", jwtToken);

        return BaseResult.ok("获取token", tokenJson);
    }

    /**
     * 刷新token
     * 1.更新创建时间
     * 2.更新过期时间
     */
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    public BaseResult refreshToken(HttpServletRequest request) {
        String oldToken = request.getHeader(this.header);
        String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);

        UserDetailsImpl jwtUser = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

        String newToken = "";
        if (jwtTokenUtil.canRefreshToken(token,new Date(jwtUser.getCtime()))){ //jwtUser.getLastPasswordResetDate()
            newToken = jwtTokenUtil.refreshToken(token);
        }
        return BaseResult.ok("刷新token", newToken);
    }
}
