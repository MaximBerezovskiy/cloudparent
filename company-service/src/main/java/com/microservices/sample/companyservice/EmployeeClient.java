package com.microservices.sample.companyservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "employee-service")
public interface EmployeeClient {
    @GetMapping("/employee/company/{id}")
    List<CompanyEmployee> findByOrganization(@PathVariable("id") Long id);
}