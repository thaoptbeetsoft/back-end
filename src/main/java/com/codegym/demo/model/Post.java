package com.codegym.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;

    @ManyToOne
    private Category category;

    private double salary;

    private String address;

    private String position;

    private String exp;

    @ManyToOne
    private WorkForm workForm;

    private Date expiredDate;

    private String description;
    @NotBlank
    private int quantity;

    @ManyToOne
    private Gender gender;

    private String code;
    private Boolean status;
    @ManyToOne
    private Company company;
    @OneToOne
    private City city;

    private boolean recommended;

    public Post() {
    }

    public Post(String title, Category category, double salary, String address, String position, String exp, WorkForm workForm, Date expiredDate, String description, int quantity, Gender gender, String code, Boolean status, Company company, City city) {
        this.title = title;
        this.category = category;
        this.salary = salary;
        this.address = address;
        this.position = position;
        this.exp = exp;
        this.workForm = workForm;
        this.expiredDate = expiredDate;
        this.description = description;
        this.quantity = quantity;
        this.gender = gender;
        this.code = code;
        this.status = status;
        this.company = company;
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public WorkForm getWorkForm() {
        return workForm;
    }

    public void setWorkForm(WorkForm workForm) {
        this.workForm = workForm;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }
}
