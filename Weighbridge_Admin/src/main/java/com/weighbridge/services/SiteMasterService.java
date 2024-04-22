package com.weighbridge.services;

import com.weighbridge.dtos.SiteMasterDto;
import com.weighbridge.payloads.SiteRequest;

import java.util.List;
import java.util.Map;

public interface SiteMasterService {

    List<SiteMasterDto> getAllSite();

    String createSite(SiteRequest siteRequest);

    List<Map<String, String>> findAllByCompanySites(String companyName);
}
