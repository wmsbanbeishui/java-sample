package com.drhs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drhs.entity.AuthMenu;
import com.drhs.service.AuthMenuService;
import com.drhs.mapper.AuthMenuMapper;
import org.springframework.stereotype.Service;

/**
* @author 1011
* @description 针对表【AUTH_MENU(菜单表)】的数据库操作Service实现
* @createDate 2023-04-12 11:34:16
*/
@Service
public class AuthMenuServiceImpl extends ServiceImpl<AuthMenuMapper, AuthMenu>
    implements AuthMenuService{

}




