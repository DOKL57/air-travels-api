package com.dokl57.airtravelsapi.service;

import com.dokl57.airtravelsapi.entity.Company;
import com.dokl57.airtravelsapi.exception.ValidationException;
import com.dokl57.airtravelsapi.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company getCompanyByName(String name) {
        Optional<Company> company = companyRepository.findCompanyByName(name);
        if (company.isPresent()) {
            return company.get();
        } else {
            log.error("Company with name {} not found", name);
            throw new ValidationException("Company with name " + name + " not found");
        }
    }

    public Company createCompany(Company company) {
        Optional<Company> existingCompany = companyRepository.findCompanyByName(company.getName());
        if (existingCompany.isPresent()) {
            log.error("Company with name {} already exists", company.getName());
            throw new ValidationException("Company with name " + company.getName() + " already exists");
        } else {
            return companyRepository.save(company);
        }
    }

    public Company updateCompany(Company company) {
        Optional<Company> existingCompany = companyRepository.findCompanyByName(company.getName());
        if (existingCompany.isPresent()) {
            return companyRepository.save(company);
        } else {
            log.error("Company with name {} not found", company.getName());
            throw new ValidationException("Company with name " + company.getName() + " not found");
        }
    }
}
