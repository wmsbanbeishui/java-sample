package com.drhs.controller;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson2.JSON;
import com.drhs.entity.Admin;
import com.drhs.secrity.CustomUser;
import com.drhs.service.AuthItemService;
import com.drhs.utils.exception.CustomException;
import com.drhs.utils.jwt.JwtHelper;
import com.drhs.service.AdminService;
import com.drhs.utils.result.ResponseParams;
import com.drhs.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "用户信息")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResponseParams<?> login(@RequestBody LoginVo loginVo) {
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

        return  ResponseParams.ok(map);




        /*Admin admin = adminService.getByUsername(loginVo.getUsername());

        String password = loginVo.getPassword();

        if (admin == null || !admin.getPassword().equals(SecureUtil.md5(password))) {
            throw new CustomException(201, "用户不存在或密码错误");
        }

        Map<String, Object> map = new HashMap<>();

        map.put("token", JwtHelper.createToken(admin.getId(), admin.getUsername()));

        List<String> userPermsList = authItemService.findUserPermsByUserId(admin.getId());

        // 保存权限数据
        stringRedisTemplate.opsForValue().set("project:" + admin.getUsername(), JSON.toJSONString(userPermsList));

        return  ResponseParams.ok(map);*/
    }
}
