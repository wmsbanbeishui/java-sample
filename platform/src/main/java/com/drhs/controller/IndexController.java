package com.drhs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "后台登录管理")
@RestController
public class IndexController {

    @ApiOperation(value = "登录")
    @GetMapping("/login")
    public String index() {
        return "Hello";
    }
}
