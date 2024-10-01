package com.example.demo.security;

import com.example.demo.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SimpleGrantedAuthority implements GrantedAuthority {

    private Role role;

    @Override
    public String getAuthority() {
        return role.getRoleName();
    }

}
