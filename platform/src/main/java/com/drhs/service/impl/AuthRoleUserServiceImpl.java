package com.drhs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drhs.entity.AuthRoleUser;
import com.drhs.service.AuthRoleUserService;
import com.drhs.mapper.AuthRoleUserMapper;
import org.springframework.stereotype.Service;

/**
* @author 1011
* @description 针对表【AUTH_ROLE_USER(角色用户表)】的数据库操作Service实现
* @createDate 2023-04-12 11:34:41
*/
@Service
public class AuthRoleUserServiceImpl extends ServiceImpl<AuthRoleUserMapper, AuthRoleUser>
    implements AuthRoleUserService{

}




