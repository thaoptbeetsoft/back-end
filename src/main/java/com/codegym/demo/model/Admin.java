package com.codegym.demo.model;

import com.codegym.demo.constant.Constant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String password;
    private Constant.TypeName type;

    public Admin(Long id, String name, String email, String password, Constant.TypeName type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }
    public Admin(@NotBlank @Size(min = 3, max = 50) String name,
                @NotBlank @Size(max = 50) @Email String email,
                @NotBlank @Size(min = 6, max = 100) String encode) {
        this.name = name;
        this.email = email;
        this.password = encode;
    }

    public Admin() {
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

    public Constant.TypeName getType() {
        return type;
    }

    public void setType(Constant.TypeName type) {
        this.type = type;
    }
}
