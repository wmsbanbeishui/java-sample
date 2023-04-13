package com.drhs.service.impl;

import com.drhs.secrity.CustomUser;
import com.drhs.secrity.UserDetailsService;
import com.drhs.entity.Admin;
import com.drhs.service.AdminService;
import com.drhs.service.AuthItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthItemService authItemService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminService.getByUsername(username);

        if (admin == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        //根据userid查询用户操作权限数据
        List<String> userPermsList = authItemService.findUserPermsByUserId(admin.getId());
        //创建list集合，封装最终权限数据
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        //查询list集合遍历
        for (String perm : userPermsList) {
            authList.add(new SimpleGrantedAuthority(perm.trim()));
        }

        return new CustomUser(admin, authList);
    }
}
