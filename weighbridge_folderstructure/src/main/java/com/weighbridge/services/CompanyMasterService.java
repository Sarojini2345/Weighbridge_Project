package com.weighbridge.services;

import com.weighbridge.dtos.CompanyMasterDto;

import java.util.List;

public interface CompanyMasterService {

    CompanyMasterDto createCompany(CompanyMasterDto companyMasterDto);

    List<CompanyMasterDto> getAllCompany();

    List<String> getAllCompanyNameOnly();
}
