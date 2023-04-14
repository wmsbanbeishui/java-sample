package com.drhs.secrity;

import com.alibaba.fastjson2.JSON;
import com.drhs.utils.exception.CustomException;
import com.drhs.utils.jwt.JwtHelper;
import com.drhs.utils.result.ResponseParams;
import com.drhs.utils.result.ResponseUtil;
import com.drhs.utils.result.ResultCodeEnum;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public JwtAuthenticationFilter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("uri:" + request.getRequestURI());
        //如果是登录接口，直接放行
        if ("/user/login".equals(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
        if (null != authentication) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } else {
            ResponseUtil.out(response, ResponseParams.build(null, ResultCodeEnum.FORBIDDEN));
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        // token置于header里
        String token = request.getHeader("token");
        logger.info("token:" + token);

        if (StringUtils.hasLength(token)) {
            Long userId = JwtHelper.getUserId(token);
            String username = JwtHelper.getUsername(token);

            logger.info("username:" + username);
            if (StringUtils.hasLength(username)) {
                //从redis中获取User，主要是用来判断该用户是否正常
                String jsonUserInfo = stringRedisTemplate.opsForValue().get("java-project:user:" + userId);
                if (!StringUtils.hasLength(jsonUserInfo)) {
                    ResponseUtil.out(response,ResponseParams.build(null, ResultCodeEnum.UNAUTHORIZED));
                }

                // 从redis获取权限数据
                String authString = stringRedisTemplate.opsForValue().get("java-project:userAuth:" + userId);

                //把redis获取字符串权限数据转换要求集合类型 List<SimpleGrantedAuthority>
                if(StringUtils.hasLength(authString)) {
                    List<Map> mapList = JSON.parseArray(authString, Map.class);

                    List<SimpleGrantedAuthority> authList = new ArrayList<>();
                    for (Map map:mapList) {
                        String authority = (String)map.get("authority");
                        authList.add(new SimpleGrantedAuthority(authority));
                    }
                    return new UsernamePasswordAuthenticationToken(username,null, authList);
                }
            } else {
                ResponseUtil.out(response,ResponseParams.build(null, ResultCodeEnum.UNAUTHORIZED));
            }

            return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());

        }
        return null;
    }
}
