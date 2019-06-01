package com.microservices.sample.companyservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/company")
@Import(FeignClientsConfiguration.class)
public class CompanyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);


    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    EmployeeClient employeeClient;

    @GetMapping("/{id}")
    public Company findById(@PathVariable("id") Long id) {
        LOGGER.info("Organization find: id={}", id);
        Company organization = companyRepository.findById(id).get();
        return organization;
    }

    @GetMapping("/{id}/with-employees")
    public Company findByIdWithEmployees(@PathVariable("id") Long id) {
        LOGGER.info("Organization find with employees: id={}", id);
        Company organization = companyRepository.findById(id).orElse(new Company());
        List<CompanyEmployee> employees = employeeClient.findByOrganization(organization.getId());
        LOGGER.info("Organization employees: id={}", employees.toArray().toString());

        organization.setEmployees(employees);
        return organization;
    }

    @Bean
    ApplicationRunner init(CompanyRepository repository) {
        return args -> {
            Stream.of("Microsoft", "Google", "BMW", "Apple", "Sun",
                    "Samsung", "AT and T", "Sony").forEach(name -> {
                Company car = new Company();
                car.setName(name);
                repository.save(car);
            });
            repository.findAll().forEach(System.out::println);
        };
    }
}

