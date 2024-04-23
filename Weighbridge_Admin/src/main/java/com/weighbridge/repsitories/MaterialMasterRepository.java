package com.weighbridge.repsitories;


import com.weighbridge.entities.MaterialMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * This repository interface for managing MaterialMaster entities in database.
 */
public interface MaterialMasterRepository extends JpaRepository<MaterialMaster, Long> {
    /**
     * Check if a material with the given name exists.
     * @param materialName The name of the material to check for existence.
     * @return True if a material with given name exists, false otherwise.
     */
    boolean existsByMaterialName(String materialName);

    /**
     *  Find a material by its name.
     * @param materialName The name of the material to find.
     * @return  The MaterialMaster entity with the specified name.
     */
    @Query("SELECT mm FROM MaterialMaster mm WHERE mm.materialName = :materialName")
    MaterialMaster findByMaterialName(@Param("materialName") String materialName);

    /**
     * Find all material names by material status.
      * @param status The status of the material to filter by.
     * @return  List of material name with the specified status.
     */
    @Query("SElECT mm.materialName FROM MaterialMaster mm WHERE mm.materialStatus = :status")
    List<String> findAllMaterialNameByMaterialStatus(@Param("status")String status);
}
