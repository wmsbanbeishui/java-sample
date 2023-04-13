package com.drhs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drhs.entity.AuthRole;
import com.drhs.service.AuthRoleService;
import com.drhs.mapper.AuthRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 1011
* @description 针对表【AUTH_ROLE(角色表)】的数据库操作Service实现
* @createDate 2023-04-12 11:34:30
*/
@Service
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRole>
    implements AuthRoleService{

}




