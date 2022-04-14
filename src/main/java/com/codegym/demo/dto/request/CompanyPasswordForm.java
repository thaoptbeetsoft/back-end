package com.codegym.demo.dto.request;

public class CompanyPasswordForm {
    private String currentPassword;
    private String newPassword;

    public CompanyPasswordForm() {
    }

    public CompanyPasswordForm(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
