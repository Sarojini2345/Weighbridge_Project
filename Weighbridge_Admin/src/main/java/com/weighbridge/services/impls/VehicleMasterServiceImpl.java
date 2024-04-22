package com.weighbridge.services.impls;

import com.weighbridge.dtos.VehicleMasterDto;
import com.weighbridge.entities.TransporterMaster;
import com.weighbridge.entities.VehicleMaster;
import com.weighbridge.exceptions.ResourceNotFoundException;
import com.weighbridge.payloads.UserResponse;
import com.weighbridge.payloads.VehicleResponse;
import com.weighbridge.repsitories.TransporterMasterRepository;
import com.weighbridge.repsitories.VehicleMasterRepository;
import com.weighbridge.services.VehicleMasterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class VehicleMasterServiceImpl implements VehicleMasterService {

    @Autowired
    VehicleMasterRepository vehicleMasterRepository;

    @Autowired
    TransporterMasterRepository transporterMasterRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    HttpServletRequest request;

//    @Override
//    public String addVehicle(VehicleMasterDto vehicleMasterDto) {
//        VehicleMaster vehicleMaster=vehicleMasterRepository.findByVehicleNo(vehicleMasterDto.getVehicleNo());
//        HttpSession session = request.getSession();
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        if(vehicleMaster==null) {
//            VehicleMaster vehicleMasters = new VehicleMaster();
//            vehicleMasters.setCreatedDate(currentDateTime);
//            vehicleMasters.setModifiedDate(currentDateTime);
//            vehicleMasters.setCreatedBy(session.getAttribute("userId").toString());
//            vehicleMasters.setModifiedBy(session.getAttribute("userId").toString());
//            vehicleMasters.setVehicleNo(vehicleMasterDto.getVehicleNo());
//
//            //assigning transporter to vehicle
//            List<TransporterMaster> transporterMasterList = new ArrayList<>();
//            transporterMasterList.addAll(getAllTransportMaster(vehicleMasterDto.getVehicleNo()));
//            //  System.out.println("--------"+getAllTransportMaster(vehicleMasterDto.getVehicleNo()));
//            TransporterMaster byTransporterName = transporterMasterRepository.findByTransporterName(vehicleMasterDto.getTransporterMaster());
//            transporterMasterList.add(byTransporterName);
//            vehicleMasters.setTransporter(transporterMasterList);
////        transporterMasterList.add(transporterMasterRepository.findByTransporterName("Maa tarini transporter"));
//
//            vehicleMasters.setVehicleManufacturer(vehicleMasterDto.getVehicleManufacturer());
//            vehicleMasters.setVehicleType(vehicleMasterDto.getVehicleType());
//            vehicleMasters.setLoadCapacity(vehicleMasterDto.getLoadCapacity());
//            vehicleMasters.setFitnessUpto(vehicleMasterDto.getFitnessUpto());
//            vehicleMasters.setWheelsNo(vehicleMasterDto.getWheelsNo());
//            vehicleMasters.setTareWeight(vehicleMasterDto.getTareWeight());
//            vehicleMasterRepository.save(vehicleMasters);
//            return "Vehicle added successfully";
//        }
//        else{
//            //assigning only transporter
//            List<TransporterMaster> transporterMasterList = new ArrayList<>();
//            transporterMasterList.addAll(getAllTransportMaster(vehicleMasterDto.getVehicleNo()));
//            //  System.out.println("--------"+getAllTransportMaster(vehicleMasterDto.getVehicleNo()));
//            TransporterMaster byTransporterName = transporterMasterRepository.findByTransporterName(vehicleMasterDto.getTransporterMaster());
//            transporterMasterList.add(byTransporterName);
//            vehicleMaster.setTransporter(transporterMasterList);
//            vehicleMaster.setModifiedBy(session.getAttribute("userId").toString());
//            vehicleMaster.setModifiedDate(currentDateTime);
//            vehicleMasterRepository.save(vehicleMaster);
//            return "transporter updated";
//        }
//    }

private List<TransporterMaster> getAllTransportMaster(String vehicleNo){
        return vehicleMasterRepository.findTransportersByVehicleId(vehicleNo);
}

    @Override
    public VehicleMaster addVehicle(VehicleMasterDto vehicleMasterDto, String transporterName) {
        TransporterMaster transporterMaster = transporterMasterRepository.findByTransporterName(transporterName);

        VehicleMaster vehicleMaster = transporterMasterRepository.findById(transporterMaster.getId()).map(transporter -> {
            VehicleMaster vm = vehicleMasterRepository.findByVehicleNo(vehicleMasterDto.getVehicleNo());
            long vehicleId = 0;
            if(vm != null){
                vehicleId = vm.getId();
            }
            // vehicle is existed
            if (vehicleId != 0L) {
                VehicleMaster vehicle = vehicleMasterRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle", "VehicleNo", vehicleMasterDto.getVehicleNo()));
                transporter.addVehicle(vehicle);
                transporterMasterRepository.save(transporter);
                return vehicle;
            }

            // add and create a new vehicle
            VehicleMaster vehicleMaster1 = new VehicleMaster();
            vehicleMaster1.setVehicleNo(vehicleMasterDto.getVehicleNo());
            vehicleMaster1.setVehicleType(vehicleMasterDto.getVehicleType());
            vehicleMaster1.setVehicleManufacturer(vehicleMasterDto.getVehicleManufacturer());
            vehicleMaster1.setVehicleLoadCapacity(vehicleMasterDto.getVehicleLoadCapacity());
            vehicleMaster1.setVehicleTareWeight(vehicleMasterDto.getVehicleTareWeight());
            vehicleMaster1.setVehicleWheelsNo(vehicleMasterDto.getVehicleWheelsNo());

            // todo: createdby, date

            transporter.addVehicle(vehicleMaster1);
            return vehicleMasterRepository.save(vehicleMaster1);
        }).orElseThrow(()-> new ResourceNotFoundException("Transporter", "transporterName", transporterName));
        return vehicleMaster;
    }

    @Override
    public Page<VehicleResponse> vehicles(Pageable pageable) {
        Page<VehicleMaster> responsePage = vehicleMasterRepository.findAll(pageable);
        Page<VehicleResponse> vehicleResponse = responsePage.map(vehicleMaster -> {
            VehicleResponse vehicleResponses = new VehicleResponse();
            vehicleResponses.setVehicleNo(vehicleMaster.getVehicleNo());
            vehicleResponses.setVehicleType(vehicleMaster.getVehicleType());
            vehicleResponses.setVehicleManufacturer(vehicleMaster.getVehicleManufacturer());

            Set<TransporterMaster> transporter = vehicleMaster.getTransporter();
            Set<String> strOfTransporter = new HashSet<>();
            for (TransporterMaster tm : transporter){
                String transporterName = tm.getTransporterName();
                strOfTransporter.add(transporterName);
            }

            vehicleResponses.setTransporter(strOfTransporter);
            vehicleResponses.setFitnessUpto(vehicleMaster.getVehicleFitnessUpTo());
            return vehicleResponses;
        });

        return vehicleResponse;
    }

//    @Override
//    public VehicleResponse vehicleByNo(String vehicleNo) {
//        VehicleMaster byId = vehicleMasterRepository.findById(vehicleNo).orElseThrow(() -> new ResourceNotFoundException("VehicleMaster", "Vehicle", vehicleNo));
//        VehicleResponse vehicleResponse = new VehicleResponse();
//        vehicleResponse.setVehicleNo(vehicleNo);
//        vehicleResponse.setVehicleManufacturer(byId.getVehicleManufacturer());
//        Set<TransporterMaster> transporters = byId.getTransporter();

//        if (!transporters.isEmpty()) {
//            // Get the last transporter from the list
//            TransporterMaster latestTransporter = transporters.get(transporters.size() - 1);
//
//            // Set the name of the latest transporter in the vehicle response
//            vehicleResponse.setTransporter(latestTransporter.getTransporterName());
//        } /*else {
            // Handle case where there are no transporters associated with the vehicle
            // You can set transporter name to null or handle it according to your requirement

//        }*/
//
//
//        vehicleResponse.setFitnessUpto(byId.getVehicleFitnessUpTo());
//        vehicleResponse.setVehicleType(byId.getVehicleType());
//        return vehicleResponse;
////    }

    //   @Override
//    public String updateVehicle(VehicleMasterDto vehicleMasterDto) {
//        VehicleMaster vehicleMaster = vehicleMasterRepository.findById(vehicleMasterDto.getVehicleNo()).get();
//        vehicleMaster.setWheelsNo(vehicleMasterDto.getWheelsNo());
//        vehicleMaster.setVehicleType(vehicleMasterDto.getVehicleType());
//        vehicleMaster.setVehicleManufacturer(vehicleMasterDto.getVehicleManufacturer());
//        vehicleMaster.setTransporter(vehicleMasterDto.getTransporterMasters());
//        vehicleMaster.setFitnessUpto(vehicleMasterDto.getFitnessUpto());
//        vehicleMaster.setLoadCapacity(vehicleMasterDto.getLoadCapacity());
//        vehicleMaster.setTareWeight(vehicleMasterDto.getTareWeight());
//        HttpSession session = request.getSession();
//        LocalDateTime dateTime=LocalDateTime.now();
//        vehicleMaster.setModifiedBy(String.valueOf(session.getAttribute("userId")));
//        vehicleMaster.setModifiedDate(dateTime);
//        vehicleMasterRepository.save(vehicleMaster);
//        return "vehicle upadated successfully";
//    }

//    @Override
//    public String deleteVehicle(String vehicleNo) {
//        VehicleMaster vehicleMaster = vehicleMasterRepository.findById(vehicleNo).get();
//        if(vehicleMaster.getStatus().equals("active")) {
//            vehicleMaster.setStatus("inactive");
//            vehicleMasterRepository.save(vehicleMaster);
//            return "vehicle deleted successfully";
//        }
//        else {
//            throw new ResourceNotFoundException("VehicleMaster","vehicle",vehicleNo);
//        }
//    }


}