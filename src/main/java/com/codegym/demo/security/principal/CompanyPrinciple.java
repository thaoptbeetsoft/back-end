package com.codegym.demo.security.principal;

import com.codegym.demo.model.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CompanyPrinciple implements UserDetails {
    private Long id;
    private String name;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> types;
    private Boolean enabled;

    public CompanyPrinciple(Long id, String name, String password, Collection<? extends GrantedAuthority> types, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.types = types;
        this.enabled = enabled;
    }

    public static CompanyPrinciple build(Company company) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(company.getType().name());
        authorities.add(authority);
        return new CompanyPrinciple(
                company.getId(),
                company.getCompanyName(),
                company.getPassword(),
                authorities,
                company.isEnabled()
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
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyPrinciple merchant = (CompanyPrinciple) o;
        return Objects.equals(id, merchant.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }





}
