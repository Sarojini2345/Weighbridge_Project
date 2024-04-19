package com.weighbridge.controllers;

import com.weighbridge.dtos.VehicleMasterDto;
import com.weighbridge.entities.VehicleMaster;
import com.weighbridge.payloads.UserResponse;
import com.weighbridge.payloads.VehicleResponse;
import com.weighbridge.services.VehicleMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleMasterController {

    @Autowired
    VehicleMasterService vehicleMasterService;

    @PostMapping("/add")
    public ResponseEntity<String> addVehicle(@RequestBody VehicleMasterDto vehicleMasterDto){
        String vehicleMsg = vehicleMasterService.addVehicle(vehicleMasterDto);
        return ResponseEntity.ok(vehicleMsg);
    }

    @GetMapping("/vehicleNo/{vehicleNo}")
    public ResponseEntity<VehicleResponse> getVehicleByVehicleNo(@PathVariable String vehicleNo){
        VehicleResponse vehicleResponse = vehicleMasterService.vehicleByNo(vehicleNo);
        return ResponseEntity.ok(vehicleResponse);
    }

//    @PutMapping("/updateVehicle")
//    public ResponseEntity<String> updateVehicle(@RequestBody VehicleMasterDto vehicleMasterDto){
//        String str = vehicleMasterService.updateVehicle(vehicleMasterDto);
//        return ResponseEntity.ok(str);
//    }


    @GetMapping("/getAllVehicles")
    public ResponseEntity<List<VehicleResponse>> getAllVehicles(  @RequestParam(defaultValue = "0", required = false) int page,
                                                                  @RequestParam(defaultValue = "10", required = false) int size,
                                                                  @RequestParam(required = false, defaultValue = "userModifiedDate") String sortField,
                                                                  @RequestParam(defaultValue = "desc", required = false) String sortOrder) {

        Pageable pageable;

        System.out.println("hjsadljfhj"+sortField+" ordre"+sortOrder);
        if (sortField != null && !sortField.isEmpty()) {
            Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            System.out.println("direction "+direction);
            Sort sort = Sort.by(direction, sortField);
            pageable = PageRequest.of(page, size, sort);
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<VehicleResponse> vehiclePage = vehicleMasterService.vehicles(pageable);
        List<VehicleResponse> vehicleLists = vehiclePage.getContent();
        return ResponseEntity.ok(vehicleLists);
    }

//   @DeleteMapping("/delete/{vehicleNo}")
//    public ResponseEntity<String> deleteVehicle(@PathVariable String vehicleNo){
//       String deletedVehicle = vehicleMasterService.deleteVehicle(vehicleNo);
//       return ResponseEntity.ok(deletedVehicle);
//   }


}
