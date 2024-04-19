package com.weighbridge.repsitories;


import com.weighbridge.entities.MaterialMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialMasterRepository extends JpaRepository<MaterialMaster, Long> {
    boolean existsByMaterialName(String materialName);

//    @Query("SELECT mm.materialName FROM MaterialMaster mm")
//    List<String> findAllMaterialName();

    @Query("SELECT mm FROM MaterialMaster mm WHERE mm.materialName = :materialName")
    MaterialMaster findByMaterialName(@Param("materialName") String materialName);

    @Query("SElECT mm.materialName FROM MaterialMaster mm WHERE mm.materialStatus = :status")
    List<String> findAllMaterialNameByMaterialStatus(@Param("status")String status);
}
