package com.drhs.secrity;

import com.drhs.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {

    private Admin admin;

    public CustomUser(Admin admin, Collection<? extends GrantedAuthority> authorities) {
        super(admin.getUsername(), admin.getPassword(), authorities);
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


}
