package com.fuyi.upms.alone.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuyi.upms.alone.auth.*;
import com.fuyi.upms.alone.bean.RespBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

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
                .formLogin().loginPage("/loginPage").loginProcessingUrl("/login")
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

                        ObjectMapper om = new ObjectMapper();
                        PrintWriter writer = response.getWriter();
                        writer.write(om.writeValueAsString(respBean));
                        writer.flush();
                        writer.close();
                })
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling()

                 // 出现AuthenticationException异常，即登录认证失败时，自定义返回信息
                .authenticationEntryPoint(authenticationEntryPoint())

                 // 鉴权失败时，自定义返回信息
                .accessDeniedHandler(accessDeniedHandler());
    }
}
