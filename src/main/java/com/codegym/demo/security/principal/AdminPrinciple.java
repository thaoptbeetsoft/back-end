package com.codegym.demo.security.principal;

import com.codegym.demo.model.Admin;
import com.codegym.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class AdminPrinciple implements UserDetails {
    private Long id;
    private String name;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> types;

    public AdminPrinciple(Long id, String name, String password, Collection<? extends GrantedAuthority> types) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.types = types;
    }

    public static AdminPrinciple build(Admin admin) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(admin.getType().name());
        authorities.add(authority);
        return new AdminPrinciple(
                admin.getId(),
                admin.getName(),
                admin.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return types;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminPrinciple adminPrinciple = (AdminPrinciple) o;
        return Objects.equals(id, adminPrinciple.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
