package com.drhs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drhs.entity.Admin;
import com.drhs.service.AdminService;
import com.drhs.mapper.AdminMapper;
import com.drhs.utils.exception.CustomException;
import com.drhs.utils.result.ResponseParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 1011
 * @description 针对表【ADMIN(管理员表)】的数据库操作Service实现
 * @createDate 2023-04-11 15:08:23
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 添加管理员
     *
     * @param admin
     * @return
     */
    @Override
    public ResponseParams<?> create(Admin admin) {
        String username = admin.getUsername();
        String password = admin.getPassword();

        if (!StringUtils.hasLength(username)) {
            throw new CustomException(201, "用户名不能为空");
        }

        if (!StringUtils.hasLength(password)) {
            throw new CustomException(201, "密码不能为空");
        }

        password = passwordEncoder.encode(password);

        admin.setUsername(username);
        admin.setPassword(password);
        this.save(admin);

        return ResponseParams.ok();
    }

    /**
     * 更新管理员
     *
     * @param admin
     * @return
     */
    @Override
    public ResponseParams<?> update(Admin admin) {
        String username = admin.getUsername();
        String password = admin.getPassword();

        if (!StringUtils.hasLength(username)) {
            throw new CustomException(301, "用户名不能为空");
        }

        if (!StringUtils.hasLength(password)) {
            throw new CustomException(301, "密码不能为空");
        }

        password = passwordEncoder.encode(password);
        admin.setPassword(password);

        this.updateById(admin);

        return ResponseParams.ok();
    }

    /**
     * 查看管理员
     *
     * @param id
     * @return
     */
    @Override
    public ResponseParams<?> view(Long id) {
        Admin admin = this.getOne(new LambdaQueryWrapper<Admin>().eq(Admin::getId, id));

        Map<String, Object> map = new HashMap<>();
        map.put("adminInfo", admin);

        return ResponseParams.ok(map);
    }

    /**
     * 删除管理员
     *
     * @param id
     * @return
     */
    @Override
    public ResponseParams<?> delete(Long id) {
        if (id == 1) {
            throw new CustomException(201, "超级管理员不能删除");
        }
        baseMapper.deleteById(id);

        stringRedisTemplate.delete("java-project:user:" + id);

        return ResponseParams.ok();
    }
}




