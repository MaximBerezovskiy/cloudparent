package com.microservices.sample.companyservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity
class Company {

    public Company(String name) {
        this.name = name;
    }

    public Company() {
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Transient
    List<CompanyEmployee> employees;

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

    public List<CompanyEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<CompanyEmployee> employees) {
        this.employees = employees;
    }
}