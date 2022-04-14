package com.codegym.demo.dto.response;

import java.util.Date;

public class PostResponse {
    private Long id;
    private String tittle;
    private String companyname;
    private String address;
    private Date expireddate;
    private double salary;

    public PostResponse(Long id, String tittle, String companyname, String address, Date expireddate, double salary) {
        this.id = id;
        this.tittle = tittle;
        this.companyname = companyname;
        this.address = address;
        this.expireddate = expireddate;
        this.salary = salary;
    }

    public PostResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getExpireddate() {
        return expireddate;
    }

    public void setExpireddate(Date expireddate) {
        this.expireddate = expireddate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
