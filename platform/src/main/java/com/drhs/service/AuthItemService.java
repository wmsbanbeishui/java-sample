package com.drhs.service;

import com.drhs.entity.AuthItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 1011
* @description 针对表【AUTH_ITEM(权限表)】的数据库操作Service
* @createDate 2023-04-12 11:33:26
*/
public interface AuthItemService extends IService<AuthItem> {
    List<String> findUserPermsByUserId(Long userId);
}
