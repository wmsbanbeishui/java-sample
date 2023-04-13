package com.drhs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drhs.entity.AuthItemMenu;
import com.drhs.service.AuthItemMenuService;
import com.drhs.mapper.AuthItemMenuMapper;
import org.springframework.stereotype.Service;

/**
* @author 1011
* @description 针对表【AUTH_ITEM_MENU(菜单权限映射表)】的数据库操作Service实现
* @createDate 2023-04-12 11:33:42
*/
@Service
public class AuthItemMenuServiceImpl extends ServiceImpl<AuthItemMenuMapper, AuthItemMenu>
    implements AuthItemMenuService{

}




