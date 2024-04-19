package com.weighbridge.repsitories;

import com.weighbridge.entities.CompanyMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyMasterRepository extends JpaRepository<CompanyMaster, String> {
    CompanyMaster findByCompanyName(String company);

    @Query("SELECT c.companyName FROM CompanyMaster c")
    List<String> findAllCompanyListName();

    long countByCompanyNameStartingWith(String companyAbbreviation);
}
