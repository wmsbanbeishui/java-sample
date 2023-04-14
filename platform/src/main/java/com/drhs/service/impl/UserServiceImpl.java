package com.drhs.service.impl;

import com.alibaba.fastjson2.JSON;
import com.drhs.entity.Admin;
import com.drhs.secrity.CustomUser;
import com.drhs.utils.exception.CustomException;
import com.drhs.utils.jwt.JwtHelper;
import com.drhs.utils.result.ResponseParams;
import com.drhs.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements com.drhs.service.UserService {

    @Autowired
    private HttpServletRequest request;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 用户登录
     * @param loginVo
     * @return
     */
    @Override
    public ResponseParams<?> login(LoginVo loginVo) {
        //封装 Authentication
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVo.getUsername(),loginVo.getPassword());

        //认证用户
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if (authenticate == null) {
            throw new CustomException(201, "用户不存在或密码错误");
        }

        CustomUser userDetails = (CustomUser)authenticate.getPrincipal();
        Admin admin = userDetails.getAdmin();

        Map<String, Object> map = new HashMap<>();
        map.put("userId", admin.getId());
        map.put("token", JwtHelper.createToken(admin.getId(), admin.getUsername()));

        // 用户信息存入redis中
        stringRedisTemplate.opsForValue().set("java-project:user:"+admin.getId(), JSON.toJSONString(admin));
        stringRedisTemplate.opsForValue().set("java-project:userAuth:"+admin.getId(), JSON.toJSONString(userDetails.getAuthorities()));

        return ResponseParams.ok(map);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public ResponseParams<?> logout() {
        Integer userId = this.getCurrentUserId();

        stringRedisTemplate.delete("java-project:user:"+userId);

        return ResponseParams.ok();
    }

    /**
     * 获取当前用户id
     * @return
     */
    @Override
    public Integer getCurrentUserId() {
        String token = request.getHeader("token");
        if (StringUtils.hasLength(token) && JwtHelper.getUserId(token) != null) {
            return JwtHelper.getUserId(token).intValue();
        }
        return null;
    }
}
