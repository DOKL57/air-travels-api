package com.dokl57.airtravelsapi.controller;

import com.dokl57.airtravelsapi.dto.CompanyDto;
import com.dokl57.airtravelsapi.entity.Company;
import com.dokl57.airtravelsapi.service.CompanyService;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompanyControllerTest {

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;


    @Test
    public void testGetAllCompanies() {
        // prepare
        when(companyService.getAllCompanies()).thenReturn(ImmutableList.of());

        // testing
        List<Company> listOfCompanies = companyController.getAllCompanies();

        // validate
        verify(companyService).getAllCompanies();

    }

    @Test
    public void testGetCompanyByName() {
        // prepare
        String name = "name";
        when(companyService.getCompanyByName(name)).thenReturn(new Company());

        // testing
        Company company = companyController.getCompanyByName(name);

        // validate
        verify(companyService).getCompanyByName(name);

    }

    @Test
    public void testCreateCompany() {
        // prepare
        String name = "name";
        CompanyDto companyDto = new CompanyDto(name);

        Company company = new Company();
        when(companyService.createCompany(name)).thenReturn(company);

        // testing
        Company createdCompany = companyController.createCompany(companyDto);

        // validate
        verify(companyService).createCompany(name);

    }

    @Test
    public void testDeleteCompany() {
        // prepare
        String name = "name";
        // testing
        companyController.deleteCompany(name);

        // validate
        verify(companyService).deleteCompany(name);

    }

}