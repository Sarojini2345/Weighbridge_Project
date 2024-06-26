package com.weighbridge.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company_master")
public class CompanyMaster {

    @Id
    @Column(name = "company_id")
    private String companyId;

    @NotBlank
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_status")
    private String companyStatus="ACTIVE";

    @Column(name = "company_created_by")
    private String companyCreatedBy;

    @Column(name = "company_created_date")
    private LocalDateTime companyCreatedDate;

    @Column(name = "company_modified_by")
    private String companyModifiedBy;

    @Column(name = "company_modified_date")
    private LocalDateTime companyModifiedDate;

}
