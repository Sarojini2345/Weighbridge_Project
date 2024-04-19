package com.weighbridge.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransporterMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transporterId;

    private String status="Active";

    private String transporterName;

    private String transporterContactNo;

    private String transporterEmailId;

    private String transporterAddress;

    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}