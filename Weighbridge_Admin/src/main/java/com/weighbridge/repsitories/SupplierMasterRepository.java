package com.weighbridge.repsitories;

import com.weighbridge.entities.SupplierMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierMasterRepository extends JpaRepository<SupplierMaster,Long> {


    boolean existsBySupplierContactNoOrSupplierEmail(String emailId, String contactNo);
}
