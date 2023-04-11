package com.drhs.controller;

import cn.hutool.crypto.SecureUtil;
import com.drhs.entity.Admin;
import com.drhs.helper.JwtHelper;
import com.drhs.service.AdminService;
import com.drhs.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户信息")
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginVo loginVo) {
        Map<String, Object> map = new HashMap<>();

        Admin admin = adminService.getByUsername(loginVo.getUsername());

        String password = loginVo.getPassword();

        if (admin == null || !admin.getPassword().equals(SecureUtil.md5(password))) {
            map.put("msg", "用户不存在或密码错误");
        }

        assert admin != null;
        String token = JwtHelper.createToken(admin.getId(), admin.getUsername());

        map.put("token", token);

        return map;
    }
}
