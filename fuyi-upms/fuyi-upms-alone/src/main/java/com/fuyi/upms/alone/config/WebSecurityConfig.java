package com.fuyi.upms.alone.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuyi.upms.alone.auth.AuthenticationAccessDeniedHandler;
import com.fuyi.upms.alone.auth.UrlAccessDecisionManager;
import com.fuyi.upms.alone.auth.UrlFilterInvocationSecurityMetadataSource;
import com.fuyi.upms.alone.auth.UserDetailsServiceImpl;
import com.fuyi.upms.alone.bean.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsServiceImpl userDetailsServiceImpl() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() {
        return new UrlFilterInvocationSecurityMetadataSource();
    }

    @Bean
    UrlAccessDecisionManager urlAccessDecisionManager() {
        return new UrlAccessDecisionManager();
    }

    @Bean
    AuthenticationAccessDeniedHandler accessDeniedHandler() {
        return new AuthenticationAccessDeniedHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl())
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**", "/login_p");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
                        object.setAccessDecisionManager(urlAccessDecisionManager());
                        return object;
                    }
                })
                .and()
                .formLogin().loginPage("/login_p").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler( (req, resp, e) -> {
                        resp.setContentType("application/json;charset=utf-8");
                        RespBean respBean;
                        if (e instanceof BadCredentialsException ||
                                e instanceof UsernameNotFoundException) {
                            respBean = RespBean.error("账户名或者密码输入错误!");
                        } else if (e instanceof LockedException) {
                            respBean = RespBean.error("账户被锁定，请联系管理员!");
                        } else if (e instanceof CredentialsExpiredException) {
                            respBean = RespBean.error("密码过期，请联系管理员!");
                        } else if (e instanceof AccountExpiredException) {
                            respBean = RespBean.error("账户过期，请联系管理员!");
                        } else if (e instanceof DisabledException) {
                            respBean = RespBean.error("账户被禁用，请联系管理员!");
                        } else {
                            respBean = RespBean.error("登录失败!");
                        }
                        resp.setStatus(401);
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString(respBean));
                        out.flush();
                        out.close();
                })
                .successHandler( (request, response, authentication) -> {
                        response.setContentType("application/json;charset=utf-8");
                        RespBean respBean = RespBean.ok("登录成功!", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                        PrintWriter writer = response.getWriter();
                        writer.write(JSON.toJSONString(respBean));
                        writer.flush();
                        writer.close();
                })
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }
}
