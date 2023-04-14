package com.drhs.service;

import com.drhs.utils.result.ResponseParams;
import com.drhs.vo.LoginVo;

public interface UserService {
    // 用户登录
    ResponseParams<?> login(LoginVo loginVo);

    // 退出登录
    ResponseParams<?> logout();

    // 获取当前用户id
    Integer getCurrentUserId();
}
