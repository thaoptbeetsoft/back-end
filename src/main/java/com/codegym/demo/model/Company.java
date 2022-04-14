package com.codegym.demo.model;

import com.codegym.demo.constant.Constant;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String companyName;
    @NotBlank
    private String shortName;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    @Lob
    private String image;

    private String phone;

    private String companyCode;
    @NotBlank
    @Lob
    private String description;

    private String address;

    private int numberOfStaff;

    private String branch;

    private String linkGoogle;

    private String website;

    private Constant.TypeName type;

    private boolean enabled;

    private boolean recommended;

    public Company() {
    }

    public Company(String companyName, String shortName, String email, String password, String image, String phone, String companyCode, String description, String address, int numberOfStaff, String branch, String linkGoogle, String website, Constant.TypeName type) {
        this.companyName = companyName;
        this.shortName = shortName;
        this.email = email;
        this.password = password;
        this.image = image;
        this.phone = phone;
        this.companyCode = companyCode;
        this.description = description;
        this.address = address;
        this.numberOfStaff = numberOfStaff;
        this.branch = branch;
        this.linkGoogle = linkGoogle;
        this.website = website;
        this.type = type;
    }

    public Company(@NotBlank @Size(min = 3, max = 50) String name,
                   @NotBlank @Size(min = 3, max = 20) String shortName,
                   @NotBlank @Size(max = 50) @Email String email,
                   @NotBlank @Size(min = 6, max = 100) String encode,
                   @NotBlank String description) {
        this.companyName = name;
        this.shortName = shortName;
        this.email = email;
        this.password = encode;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfStaff() {
        return numberOfStaff;
    }

    public void setNumberOfStaff(int numberOfStaff) {
        this.numberOfStaff = numberOfStaff;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getLinkGoogle() {
        return linkGoogle;
    }

    public void setLinkGoogle(String linkGoogle) {
        this.linkGoogle = linkGoogle;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Constant.TypeName getType() {
        return type;
    }

    public void setType(Constant.TypeName type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }
}
