package com.dokl57.airtravelsapi.service;

import com.dokl57.airtravelsapi.dto.TripDto;
import com.dokl57.airtravelsapi.entity.Company;
import com.dokl57.airtravelsapi.entity.PassInTrip;
import com.dokl57.airtravelsapi.entity.Trip;
import com.dokl57.airtravelsapi.exception.ValidationException;
import com.dokl57.airtravelsapi.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Company createCompany(String name) {
        Optional<Company> existingCompany = companyRepository.findCompanyByName(name);
        if (existingCompany.isPresent()) {
            log.error("Company with name {} already exists", name);
            throw new ValidationException("Company with name " + name + " already exists");
        } else {
            return companyRepository.save(new Company(UUID.randomUUID(), name, new HashSet<>()));
        }
    }

    public void deleteCompany(String name) {
        Optional<Company> existingCompany = companyRepository.findCompanyByName(name);
        if (existingCompany.isPresent()) {
            companyRepository.delete(existingCompany.get());
        } else {
            log.error("Company with name {} not found", name);
            throw new ValidationException("Company with name " + name + " not found");
        }
    }

    public void addTripToCompany(String companyName, String townFrom, String townTo, LocalDateTime timeIn, LocalDateTime timeOut) {
        Optional<Company> existingCompany = companyRepository.findCompanyByName(companyName);
        if (existingCompany.isPresent()) {
            existingCompany.get().addTripToCompany(new Trip(UUID.randomUUID(), townFrom, townTo, timeIn, timeOut, new HashSet<>(), existingCompany.get()));
            companyRepository.save(existingCompany.get());
        } else {
            log.error("Company with name {} not found", companyName);
            throw new ValidationException("Company with name " + companyName + " not found");
        }
    }

    public Iterable<Company> getAllCompanies() {
        return companyRepository.findAll();
    }



}
