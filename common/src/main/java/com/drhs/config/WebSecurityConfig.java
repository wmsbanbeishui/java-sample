package com.drhs.config;

import com.drhs.secrity.CustomMd5PasswordEncoder;
import com.drhs.secrity.JwtAuthenticationFilter;
import com.drhs.secrity.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomMd5PasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 基于 token，不需要 csrf
                .csrf().disable()
                // 开启跨域以便前端调用接口
                .cors().and()
                // 基于 token，不需要 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 设置 jwtAuthError 处理认证失败、鉴权失败
                //.exceptionHandling().authenticationEntryPoint(jwtAuthError).accessDeniedHandler(jwtAuthError).and()
                // 下面开始设置权限
                .authorizeRequests()
                .antMatchers("/user/login").permitAll()
                //.antMatchers("/admin/sysUser/create").permitAll()
                .anyRequest().authenticated()
                .and()
                // 添加 JWT 过滤器，JWT 过滤器在用户名密码认证过滤器之前
                .addFilterBefore(new JwtAuthenticationFilter(stringRedisTemplate), UsernamePasswordAuthenticationFilter.class)
                // 认证用户时用户信息加载配置，注入springAuthUserService
                .userDetailsService(userDetailsService)
        ;

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->web.ignoring().antMatchers(
                "/admin/modeler/**",
                "/diagram-viewer/**",
                "/editor-app/**",
                "/*.html",
                "/admin/processImage/**",
                "/admin/wechat/authorize",
                "/admin/wechat/userInfo",
                "/admin/wechat/bindPhone",
                "/favicon.ico",
                "/swagger-resources/**",
                "/webjars/**",
                "/v3/**",
                "/swagger-ui.html/**",
                "/doc.html",
                "/druid/**"
        );
    }
}
