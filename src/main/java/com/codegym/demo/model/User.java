package com.codegym.demo.model;

import com.codegym.demo.constant.Constant;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String password;

    private String phone;

    private Boolean enabled;

    private Constant.TypeName type;


    public User() {
    }

    public User(Long id, String name, String email, String password, String phone, Constant.TypeName type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.type = type;
    }

    public User(@NotBlank @Size(min = 3, max = 50) String name,
                @NotBlank @Size(max = 50) @Email String email,
                @NotBlank @Size(min = 6, max = 100) String encode,
                @Pattern(regexp = "^[+84]+[0-9]{9}$") String phone) {
        this.name = name;
        this.email = email;
        this.password = encode;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Constant.TypeName getType() {
        return type;
    }

    public void setType(Constant.TypeName type) {
        this.type = type;
    }

}
