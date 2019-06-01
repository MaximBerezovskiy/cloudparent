package com.microservices.sample.companyservice;


import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class EmployeeClientFallback implements EmployeeClient {
    @Override
    public List<CompanyEmployee> findByOrganization(Long id) {
        CompanyEmployee companyEmployee = new CompanyEmployee();
        companyEmployee.setName("Fallback");
        return Collections.singletonList(companyEmployee);
    }

}