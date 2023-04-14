package com.drhs.controller;

import com.drhs.entity.Admin;
import com.drhs.service.AdminService;
import com.drhs.utils.result.ResponseParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "管理员管理")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AdminService adminService;

    /*@PreAuthorize("hasAuthority('/admin/sysUser/create')")
    @ApiOperation(value = "添加管理员")
    @PostMapping("/create")
    public ResponseParams<?> create() {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (!StringUtils.hasLength(username)) {
            throw new CustomException(201, "用户名不能为空");
        }

        if (!StringUtils.hasLength(password)) {
            throw new CustomException(201, "密码不能为空");
        }

        password = passwordEncoder.encode(password);

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        adminService.save(admin);

        return ResponseParams.ok();
    }*/

    @ApiOperation(value = "添加管理员")
    @PreAuthorize("hasAuthority('/admin/create')")
    @PostMapping("/create")
    public ResponseParams<?> create(@RequestBody Admin admin) {
        return adminService.create(admin);
    }

    @ApiOperation(value = "更新管理员")
    @PreAuthorize("hasAuthority('/admin/update')")
    @PostMapping("/update")
    public ResponseParams<?> update(@RequestBody Admin admin) {
        return adminService.update(admin);
    }

    @ApiOperation(value = "查看管理员")
    @PreAuthorize("hasAuthority('/admin/view')")
    @GetMapping("/view/{id}")
    public ResponseParams<?> view(@PathVariable("id") Long id) {
        return adminService.view(id);
    }

    @ApiOperation(value = "删除管理员")
    @PreAuthorize("hasAuthority('/admin/delete')")
    @GetMapping("/delete/{id}")
    public ResponseParams<?> delete(@PathVariable("id") Long id) {
        return adminService.delete(id);
    }
}
