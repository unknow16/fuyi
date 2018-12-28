package com.fuyi.upms.alone.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuyi.framework.web.base.BaseResult;
import com.fuyi.upms.alone.auth.*;
import com.fuyi.upms.alone.bean.RespBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.http.HttpServletResponse;
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

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return new UserAuthenticationEntryPoint();
    }

    /**
     * new BCryptPasswordEncoder()
     * NoOpPasswordEncoder.getInstance()
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl())
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/", "/index.html", "/swagger-ui.html", "/static/**", "/login_p")
                    // swagger start
                    .antMatchers("/swagger-ui.html")
                    .antMatchers("/swagger-resources/**")
                    .antMatchers("/images/**")
                    .antMatchers("/webjars/**")
                    .antMatchers("/v2/api-docs")
                    .antMatchers("/configuration/ui")
                    .antMatchers("/configuration/security");
                    // swagger end;
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
                .csrf().disable() // 由于使用的是JWT，我们这里不需要csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // 基于token，所以不需要session

                /**
                 * form 表单登陆配置
                 */
//                .formLogin().loginPage("/loginPage").loginProcessingUrl("/login")
//                .usernameParameter("username").passwordParameter("password")
//
//                // 登录失败，这里可以自定义返回的错误信息
//                .failureHandler( (req, resp, authException) -> {
//                        resp.setContentType("application/json;charset=utf-8");
//                        RespBean respBean;
//                        if (authException instanceof BadCredentialsException ||
//                                authException instanceof UsernameNotFoundException) {
//                            respBean = RespBean.error("账户名或者密码输入错误!");
//                        } else if (authException instanceof LockedException) {
//                            respBean = RespBean.error("账户被锁定，请联系管理员!");
//                        } else if (authException instanceof CredentialsExpiredException) {
//                            respBean = RespBean.error("密码过期，请联系管理员!");
//                        } else if (authException instanceof AccountExpiredException) {
//                            respBean = RespBean.error("账户过期，请联系管理员!");
//                        } else if (authException instanceof DisabledException) {
//                            respBean = RespBean.error("账户被禁用，请联系管理员!");
//                        } else {
//                            respBean = RespBean.error("登录失败!");
//                        }
//                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        ObjectMapper om = new ObjectMapper();
//                        PrintWriter out = resp.getWriter();
//                        out.write(om.writeValueAsString(respBean));
//                        out.flush();
//                        out.close();
//                })
//
//                // 登录成功，这里可以自定义返回的成功信息。
//                .successHandler( (request, response, authentication) -> {
//                        response.setContentType("application/json;charset=utf-8");
//                        response.setStatus(HttpServletResponse.SC_OK);
//
//                        ObjectMapper om = new ObjectMapper();
//                        PrintWriter writer = response.getWriter();
//                        writer.write(om.writeValueAsString(BaseResult.ok("登录成功", SecurityContextHolder.getContext().getAuthentication().getPrincipal())));
//                        writer.flush();
//                        writer.close();
//                }).and()

                /**
                 * 认证请求配置
                 */
                .authorizeRequests()

                // 允许对于网站静态资源的无授权访问
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()

                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/auth/**").permitAll()

                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated().and()

                /**
                 * 异常处理器配置
                 */
                .exceptionHandling()

                 // 出现AuthenticationException异常，即登录认证失败时，自定义返回信息
                 // 当用户没有权限访问某个资源的时候，你可以在这里自定义返回内容。
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401

                    ObjectMapper om = new ObjectMapper();
                    PrintWriter out = response.getWriter();
                    out.write(om.writeValueAsString(BaseResult.ok("未登录,请先登录")));
                    out.flush();
                    out.close();
                })

                 // 鉴权失败时，自定义返回信息
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403

                    ObjectMapper om = new ObjectMapper();
                    PrintWriter out = response.getWriter();
                    out.write(om.writeValueAsString(BaseResult.ok("权限不足,请联系管理员")));
                    out.flush();
                    out.close();
                });
    }
}
