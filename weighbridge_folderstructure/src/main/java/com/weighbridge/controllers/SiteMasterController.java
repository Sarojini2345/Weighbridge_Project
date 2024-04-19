package com.weighbridge.controllers;

import com.weighbridge.dtos.SiteMasterDto;
import com.weighbridge.payloads.SiteRequest;
import com.weighbridge.services.SiteMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sites")
public class SiteMasterController {

    private final SiteMasterService siteMasterService;

//    @PostMapping
//    public ResponseEntity<SiteMasterDto> createSite(@Validated @RequestBody SiteMasterDto siteMasterDto){
//        SiteMasterDto savedSite = siteMasterService.createSite(siteMasterDto);
//        return new ResponseEntity<>(savedSite, HttpStatus.CREATED);
//    }

    @PostMapping("/assignCompany")
    public ResponseEntity<String> assignSite(@Validated @RequestBody SiteRequest siteRequest){
        String response = siteMasterService.assignSite(siteRequest);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<SiteMasterDto>> GetAllSites(){
        List<SiteMasterDto> savedSites = siteMasterService.getAllSite();
        return ResponseEntity.ok(savedSites);
    }



    // todo: create method to get all the sites as a list of strings
    @GetMapping("/company/{companyName}")
    public ResponseEntity<List<Map<String, String>>> GetAllSitesOfCompany(@PathVariable String companyName){
        List<Map<String, String>> allByCompanySites = siteMasterService.findAllByCompanySites(companyName);
        return ResponseEntity.ok(allByCompanySites);
    }
}