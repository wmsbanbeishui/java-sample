package com.drhs.controller;

import cn.hutool.crypto.SecureUtil;
import com.drhs.entity.Admin;
import com.drhs.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "管理员管理")
@RestController
@RequestMapping("/admin/sysUser")
public class AdminController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "添加管理员")
    @PostMapping("/create")
    public String create() {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = SecureUtil.md5(password);

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        adminService.save(admin);

        return "操作成功";
    }

    @ApiOperation(value = "更新管理员")
    @PostMapping("/update")
    public String update(@RequestBody Admin admin) {
        String password = admin.getPassword();
        password = SecureUtil.md5(password);
        admin.setPassword(password);

        adminService.updateById(admin);

        return "操作成功";
    }
}
