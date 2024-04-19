package com.weighbridge.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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