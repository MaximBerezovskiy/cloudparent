package com.microservices.sample.employeeservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);


    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        LOGGER.info("Organization find: id={}", id);
        Employee employee = employeeRepository.findById(id).get();
        return employee;
    }

    @GetMapping("/company/{id}")
    public List<Employee> findByCompanyId(@PathVariable("id") Long id) {
        LOGGER.info("Organization find: id={}", id);
        List<Employee> employees = employeeRepository.findByCompanyId(id);
        return employees;
    }

    @Bean
    ApplicationRunner init(EmployeeRepository repository) {
        return args -> {
            Stream.of("Bill Gates", "Maxim Berezovskiy", "Sunil Gupta", "Alex Berezovskiy", "Stive Jobs",
                    "Martin Fowler", "Joshua Bloch").forEach(name -> {
                Employee employee = new Employee(name,Long.valueOf(1));
                repository.save(employee);
            });
            repository.save(new Employee("Admin",Long.valueOf(2)));
            repository.save(new Employee("Guest",Long.valueOf(3)));


            repository.findAll().forEach(System.out::println);
        };
    }


}

