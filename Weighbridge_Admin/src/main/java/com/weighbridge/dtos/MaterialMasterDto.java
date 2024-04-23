package com.weighbridge.dtos;

import lombok.Data;

/**
 * This DTO class representing material master information.
 */
@Data
public class MaterialMasterDto {
    private long materialId; // Unique identifier for the material
    private String materialName; // Name of the material
    private String materialStatus; // Status of the material (e.g., ACTIVE, INACTIVE)
    private String materialCreatedBy; // Name of the user who created the material
    private String materialCreatedDate; // Date when the material was created
    private String materialModifiedBy; // Name of the user who last modified the material
    private String materialModifiedDate; // Date when the material was last modified
}

