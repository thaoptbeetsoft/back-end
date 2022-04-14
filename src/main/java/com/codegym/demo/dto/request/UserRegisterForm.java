package com.codegym.demo.dto.request;


import com.codegym.demo.constant.Constant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterForm {
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
    @Pattern(regexp = "^[+84]+[0-9]{9}$")
    private String phone;
    private Constant.TypeName type;

    public UserRegisterForm() {
    }

    public UserRegisterForm(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
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

    public Constant.TypeName getType() {
        return type;
    }

    public void setType(Constant.TypeName type) {
        this.type = type;
    }
}
