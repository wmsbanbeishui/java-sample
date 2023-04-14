package com.drhs.service;

import com.drhs.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drhs.utils.result.ResponseParams;
import org.springframework.web.bind.annotation.RequestBody;

/**
* @author 1011
* @description 针对表【ADMIN(管理员表)】的数据库操作Service
* @createDate 2023-04-11 15:08:23
*/
public interface AdminService extends IService<Admin> {

    // 添加管理员
    ResponseParams<?> create(Admin admin);

    // 更新管理员
    ResponseParams<?> update(Admin admin);

    // 查看管理员
    ResponseParams<?> view(Long id);

    // 删除管理员
    ResponseParams<?> delete(Long id);
}
