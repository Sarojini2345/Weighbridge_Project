package com.weighbridge.repsitories;

import com.weighbridge.entities.SiteMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SiteMasterRepository extends JpaRepository<SiteMaster, String> {
    @Query("SELECT new map(s.siteName as siteName, s.siteAddress as siteAddress) FROM SiteMaster s WHERE s.company.companyId = :companyId")
    List<Map<String, String>> findAllByCompanyId(@Param("companyId") String companyId);

    List<SiteMaster> findBySiteName(String site);

    long countBySiteNameStartingWith(String siteAbbreviation);

    SiteMaster findBySiteNameAndSiteAddress(String siteName, String siteAddress);


}
