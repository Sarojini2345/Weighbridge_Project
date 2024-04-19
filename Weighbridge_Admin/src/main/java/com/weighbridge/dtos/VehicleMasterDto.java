package com.weighbridge.dtos;

import com.weighbridge.entities.TransporterMaster;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class VehicleMasterDto {
    private String vehicleNo;

    private String vehicleType;

    private String vehicleManufacturer;

    private Integer wheelsNo;

    private Double tareWeight;

    private Double loadCapacity;

    private Date fitnessUpto;

    private String transporterMaster;

    private String createdBy;
    private  String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}