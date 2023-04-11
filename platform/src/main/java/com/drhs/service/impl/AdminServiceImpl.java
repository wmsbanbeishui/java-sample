package com.drhs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drhs.entity.Admin;
import com.drhs.service.AdminService;
import com.drhs.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author 1011
* @description 针对表【ADMIN(管理员表)】的数据库操作Service实现
* @createDate 2023-04-11 15:08:23
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

    @Override
    public Admin getByUsername(String username) {
        return this.getOne(new LambdaQueryWrapper<Admin>().eq(Admin::getUsername, username));
    }
}




