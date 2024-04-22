package com.weighbridge.repsitories;

import com.weighbridge.entities.TransporterMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransporterMasterRepository extends JpaRepository<TransporterMaster, Long> {


    Boolean existsByTransporterName(String transporterName);

    TransporterMaster findByTransporterName(String transporterName);



    List<TransporterMaster> findTransporterMastersByVehiclesId(Long vehicleId);

}
