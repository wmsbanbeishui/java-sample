package com.drhs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drhs.entity.AuthItem;
import com.drhs.service.AuthItemService;
import com.drhs.mapper.AuthItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author 1011
* @description 针对表【AUTH_ITEM(权限表)】的数据库操作Service实现
* @createDate 2023-04-12 11:33:26
*/
@Service
public class AuthItemServiceImpl extends ServiceImpl<AuthItemMapper, AuthItem>
    implements AuthItemService{

    @Autowired
    private AuthItemMapper authItemMapper;

    @Override
    public List<String> findUserPermsByUserId(Long userId) {
        //1 判断是否是管理员，如果是管理员，查询所有按钮列表
        List<AuthItem> authItemList = null;

        if(userId == 1) {
            //查询所有菜单列表
            authItemList = this.list();
        } else {
            //2 如果不是管理员，根据userId查询可以操作按钮列表
            //多表关联查询：用户角色关系表 、 角色菜单关系表、 菜单表
            authItemList = authItemMapper.findAuthListByUserId(userId);
        }

        //3 从查询出来的数据里面，获取可以操作按钮值的list集合，返回
        List<String> permsList = authItemList.stream()
                .map(AuthItem::getName)
                .collect(Collectors.toList());

        return permsList;
    }
}




