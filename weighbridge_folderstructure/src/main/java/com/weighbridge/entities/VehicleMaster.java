package com.weighbridge.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class VehicleMaster {
    @Id
    private String vehicleNo;

    private String status="Active";
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "transporter_vehicle",
            joinColumns = @JoinColumn(name = "vehicleNo", referencedColumnName = "vehicleNo"),
            inverseJoinColumns = @JoinColumn(name = "transporter_id", referencedColumnName = "transporterId")
    )
    private List<TransporterMaster> transporter;

    private String vehicleType;

    private String vehicleManufacturer;

    private Integer wheelsNo;

    private Double tareWeight;

    private Double loadCapacity;

    private Date fitnessUpto;

    private String createdBy;
    private  String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}