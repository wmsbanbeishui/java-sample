package com.drhs.controller;

import com.drhs.service.UserService;
import com.drhs.utils.result.ResponseParams;
import com.drhs.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户信息")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResponseParams<?> login(@RequestBody LoginVo loginVo) {
        return userService.login(loginVo);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public ResponseParams<?> logout() {
        return userService.logout();
    }
}


//登录时，将用户信息、权限信息写入redis
//退出登录时，将redis中的用户信息删除
//
//
//用户状态有变化时，比如删除或禁用时，要删除redis中的用户信息
//
//
//用户权限有变化时，要更新redis中的用户权限信息
