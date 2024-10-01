package com.example.demo.security;

import com.example.demo.Role;
import com.example.demo.model.Farmer;
import com.example.demo.model.Inspector;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final String email;
    private final String password;
    private final Role role;

    public CustomUserDetails(Farmer farmer) {
        this.email = farmer.getEmail();
        this.password = farmer.getPassword();
        this.role = farmer.getRole();
    }

    public CustomUserDetails(Inspector inspector) {
        this.email = inspector.getEmail();
        this.password = inspector.getPassword();
        this.role = inspector.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
