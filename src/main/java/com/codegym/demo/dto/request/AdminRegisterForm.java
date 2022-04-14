package com.codegym.demo.dto.request;

import com.codegym.demo.constant.Constant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AdminRegisterForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
    private Constant.TypeName type;

    public AdminRegisterForm() {
    }

    public AdminRegisterForm(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public Constant.TypeName getType() {
        return type;
    }

    public void setType(Constant.TypeName type) {
        this.type = type;
    }
}
