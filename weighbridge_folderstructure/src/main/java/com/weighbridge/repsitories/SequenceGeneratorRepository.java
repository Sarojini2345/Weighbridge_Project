package com.weighbridge.repsitories;

import com.weighbridge.entities.SequenceGenerator;
import com.weighbridge.entities.SequenceGeneratorPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SequenceGeneratorRepository extends JpaRepository<SequenceGenerator, SequenceGeneratorPK> {


    Optional<SequenceGenerator> findByCompanyIdAndSiteId(String companyId, String siteId);
}
