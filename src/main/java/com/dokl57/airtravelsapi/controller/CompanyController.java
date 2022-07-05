package com.dokl57.airtravelsapi.controller;

import com.dokl57.airtravelsapi.dto.CompanyDto;
import com.dokl57.airtravelsapi.entity.Company;
import com.dokl57.airtravelsapi.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;

@RestController
@Slf4j
@Validated
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /*
       POST /api/companies/create - create new company
     */
    @PostMapping(value = "/create")
    Company createCompany(@RequestBody @NotNull CompanyDto companyDto) {
        log.info("Creating company with name {}", companyDto.getName());
        return companyService.createCompany(companyDto.getName());
    }

    /*
       PUT /api/companies/update - update company
     */
    @PostMapping(value = "/update")
    Company updateCompany(@RequestBody @NotNull CompanyDto companyDto) {
        log.info("Updating company with name {}", companyDto.getName());
        return companyService.updateCompany(companyDto.getName());
    }

    /*
       DELETE api/companies/delete/{name} - delete company by name
     */
    @DeleteMapping(value = "/delete/{name}")
    void deleteCompany(@PathVariable String name) {
        log.info("Deleting company with name {}", name);
        companyService.deleteCompany(name);
    }

    /*
       GET api/companies/{name} - get company by name
     */
    @GetMapping(value = "/companies/{name}")
    Company getCompanyByName(@PathVariable String name) {
        log.info("Getting company with name {}", name);
        return companyService.getCompanyByName(name);
    }

    /*
    GET api/companies - get all companies
     */
    @GetMapping(value = "/companies")
    Iterable<Company> getAllCompanies() {
        log.info("Getting all companies");
        return companyService.getAllCompanies();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("{\n \"exception\" : \"" + e.getMessage() + "\"\n}", HttpStatus.BAD_REQUEST);
    }
}
