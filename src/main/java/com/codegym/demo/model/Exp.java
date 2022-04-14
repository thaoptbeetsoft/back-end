package com.codegym.demo.model;

import javax.persistence.*;

@Entity
public class Exp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skill;
    private String year;
    private Integer salary;
    @ManyToOne
    private User user;

    public Exp() {
    }

    public Exp(Long id, String skill, String year, Integer salary, User user) {
        this.id = id;
        this.skill = skill;
        this.year = year;
        this.salary = salary;
        this.user = user;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String work) {
        this.skill = work;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}