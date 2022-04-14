package com.codegym.demo.dto.request;


import com.codegym.demo.constant.Constant;

import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CompanyRegisterForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String companyName;
    @NotBlank
    @Size(min = 3, max = 20)
    private String shortName;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
    @NotBlank
    @Lob
    private String description;

    private Constant.TypeName type;

    public CompanyRegisterForm() {
    }

    public CompanyRegisterForm(String companyName, String shortName, String email, String password, String description) {
        this.companyName = companyName;
        this.shortName = shortName;
        this.email = email;
        this.password = password;
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Constant.TypeName getType() {
        return type;
    }

    public void setType(Constant.TypeName type) {
        this.type = type;
    }
}
