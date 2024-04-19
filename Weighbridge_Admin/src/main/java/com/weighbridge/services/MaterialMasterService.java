package com.weighbridge.services;

import com.weighbridge.dtos.MaterialMasterDto;

import java.util.List;

public interface MaterialMasterService {
    MaterialMasterDto saveMaterials(MaterialMasterDto materialMasterDto);

    List<MaterialMasterDto> getAllMaterials();

    List<String> getAllMaterialNames();

    void deleteMaterial(String materialId);
}
