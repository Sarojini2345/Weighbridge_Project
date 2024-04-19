package com.weighbridge.controllers;

import com.weighbridge.dtos.CompanyMasterDto;
import com.weighbridge.services.CompanyMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
public class CompanyMasterController {

    private final CompanyMasterService companyMasterService;

    @PostMapping
    public ResponseEntity<CompanyMasterDto> createCompany(@Validated @RequestBody CompanyMasterDto companyMasterDto){
        CompanyMasterDto savedCompany = companyMasterService.createCompany(companyMasterDto);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompanyMasterDto>> GetAllCompany(){
        List<CompanyMasterDto> savedCompany = companyMasterService.getAllCompany();
        return ResponseEntity.ok(savedCompany);
    }

    @GetMapping("/get/list")
    public ResponseEntity<List<String>> getAllListStringCompanyName(){
        List<String> allCompanyNameOnly = companyMasterService.getAllCompanyNameOnly();
        return ResponseEntity.ok(allCompanyNameOnly);
    }
}
