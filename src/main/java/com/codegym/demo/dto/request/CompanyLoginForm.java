package com.codegym.demo.dto.request;

public class CompanyLoginForm {
    private String email;
    private String password;

    public CompanyLoginForm() {
    }

    public CompanyLoginForm(String email, String password) {
        this.email = email;
        this.password = password;
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
}
