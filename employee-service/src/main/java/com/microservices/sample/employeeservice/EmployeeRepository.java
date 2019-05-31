package com.microservices.sample.employeeservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByCompanyId(Long companyId);
}