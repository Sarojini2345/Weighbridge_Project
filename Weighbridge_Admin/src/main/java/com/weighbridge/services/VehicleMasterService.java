package com.weighbridge.services;

import com.weighbridge.dtos.VehicleMasterDto;
import com.weighbridge.entities.VehicleMaster;
import com.weighbridge.payloads.VehicleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface VehicleMasterService {

    VehicleMaster addVehicle(VehicleMasterDto vehicleMasterDto, String transporterName);

    Page<VehicleResponse> vehicles(Pageable pageable);

//    public VehicleResponse vehicleByNo(String vehicleNo);

    // public String updateVehicle(VehicleMasterDto vehicleMasterDto);

    //  public String deleteVehicle(String vehicleNo);
}
