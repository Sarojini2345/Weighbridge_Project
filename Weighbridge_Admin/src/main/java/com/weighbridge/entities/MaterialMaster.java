package com.weighbridge.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * This entity class representing material master data that stored in database.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "material_master")
public class MaterialMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private long materialId; // Unique identifier for the material

    @Column(name = "material_name")
    private String materialName; // Name of the material

    @Column(name = "material_status")
    private String materialStatus="ACTIVE"; // Status of the material, default value is "ACTIVE"

    @Column(name = "material_created_by")
    private String materialCreatedBy; // Name of the user, who created the material

    @Column(name = "material_created_date")
    private LocalDateTime materialCreatedDate; // Date when the material was created

    @Column(name = "material_modified_by")
    private String materialModifiedBy; // Name of the user, who last modified the material

    @Column(name = "material_modified_date")
    private LocalDateTime materialModifiedDate; // Date when the material was last modified
}
