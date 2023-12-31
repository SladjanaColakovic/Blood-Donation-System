package com.example.blooddonationsystem.security.helper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private String role;
    private Timestamp lastPasswordResetDate;

    public CustomUserDetails(String username, String password, String role, Timestamp lastPasswordResetDate) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String authority;

        switch (role) {
            case "DONOR":
                authority = "ROLE_DONOR";
                break;
            case "ADMIN":
                authority = "ROLE_ADMIN";
                break;
            default:
                authority = "ROLE_MANAGER";
        }
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);
        authorities.add(grantedAuthority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}
