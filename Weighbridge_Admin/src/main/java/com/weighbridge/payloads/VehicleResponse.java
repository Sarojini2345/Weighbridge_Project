package com.weighbridge.payloads;

import lombok.Data;

import java.util.Date;

@Data
public class VehicleResponse {
    private String vehicleNo;
    private  String transporter;
    private String vehicleType;
    private String vehicleManufacturer;
    private Date fitnessUpto;
}
