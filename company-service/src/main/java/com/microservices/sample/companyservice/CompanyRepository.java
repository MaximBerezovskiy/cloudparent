package com.microservices.sample.companyservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface CompanyRepository extends JpaRepository<Company, Long> {
}