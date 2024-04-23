package com.weighbridge.services;

import com.weighbridge.dtos.MaterialMasterDto;

import java.util.List;

/**
 * This Service interface for managing material master data.
 */
public interface MaterialMasterService {
    /**
     * Save a material using the provided DTO.
     * @param materialMasterDto The DTO containing material master information to be saved.
     * @return The saved MaterialMasterDto.
     */
    MaterialMasterDto saveMaterials(MaterialMasterDto materialMasterDto);

    /**
     * Retrieve all materials as DTOs.
     * @return List of MaterialMaster objects representing all materials.
     */
    List<MaterialMasterDto> getAllMaterials();

    /**
     * Get all material names.
     * @return List of strings containing all material names.
     */
    List<String> getAllMaterialNames();

    /**
     * Delete a material by its ID.
     * @param materialId The ID of the material to be deleted.
     */
    void deleteMaterial(String materialId);
}
