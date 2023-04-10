package com.drhs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drhs.entity.Employees;
import com.drhs.service.EmployeesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "后台登录管理")
@RestController
public class IndexController {

    @Autowired
    private EmployeesService employeesService;

    @ApiOperation(value = "登录")
    @GetMapping("/login")
    public String index() {
        return "Hello";
    }

    @ApiOperation(value = "员工列表")
    @GetMapping("/employee/list/{page}/{limit}")
    public Map<String, Object> employeeList(@PathVariable Long page, @PathVariable Long limit) {
        Map<String, Object> map = new HashMap<>();

        Page<Employees> pageParam = new Page<>(page, limit);

        IPage<Employees> pageModel = employeesService.page(pageParam);

        map.put("data", pageModel);

        return map;
    }
}
