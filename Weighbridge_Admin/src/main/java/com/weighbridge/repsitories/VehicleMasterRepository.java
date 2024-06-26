package com.weighbridge.repsitories;

import com.weighbridge.entities.TransporterMaster;
import com.weighbridge.entities.VehicleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.nio.ByteBuffer;
import java.util.List;

public interface VehicleMasterRepository extends JpaRepository<VehicleMaster,Long> {


    @Query("SELECT v.transporter FROM VehicleMaster v WHERE v.vehicleNo = :vehicleId")
    List<TransporterMaster> findTransportersByVehicleId(String vehicleId);

//    VehicleMaster findByVehicleNo(String vehicleNo);
//
//    boolean existsByVehicleNo(String vehicleNo);

    List<VehicleMaster> findVehicleMastersByTransporterId(Long transporterId);

    VehicleMaster findByVehicleNo(String vehicleNo);
}
