package com.weighbridge.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VehicleMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String vehicleNo;
    private String vehicleType;
    private String vehicleManufacturer;
    private Integer vehicleWheelsNo;
    private Double vehicleTareWeight;
    private Double vehicleLoadCapacity;
    private Date vehicleFitnessUpTo;
    private String vehicleStatus = "ACTIVE";
    private String vehicleCreatedBy;
    private LocalDateTime vehicleCreatedDate;
    private String vehicleModifiedBy;
    private LocalDateTime vehicleModifiedDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "vehicles")
    private Set<TransporterMaster> transporter = new HashSet<>();

}