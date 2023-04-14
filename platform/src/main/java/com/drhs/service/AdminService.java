package com.drhs.service;

import com.drhs.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 1011
* @description 针对表【ADMIN(管理员表)】的数据库操作Service
* @createDate 2023-04-11 15:08:23
*/
public interface AdminService extends IService<Admin> {

    // 根据用户名获取用户信息
    Admin getByUsername(String username);
}
