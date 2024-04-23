package com.weighbridge.services.impls;

import com.weighbridge.dtos.MaterialMasterDto;
import com.weighbridge.entities.MaterialMaster;
import com.weighbridge.repsitories.MaterialMasterRepository;
import com.weighbridge.services.MaterialMasterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is the implementation of MaterialService for managing material master data.
 */
@Service
public class MaterialMasterServiceImpl implements MaterialMasterService {
    private MaterialMasterRepository materialMasterRepository;
    private ModelMapper modelMapper;
    private HttpServletRequest httpServletRequest;

    /**
     * Constructor for MaterialMasterServiceImpl class to inject bean.
     * @param materialMasterRepository The repository for MaterialMaster entities.
     * @param modelMapper The ModelMapper for Entity-Dto mapping.
     * @param httpServletRequest The HttpServletRequest for session management.
     */
    public MaterialMasterServiceImpl(MaterialMasterRepository materialMasterRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest) {
        this.materialMasterRepository = materialMasterRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * Saved materials based on provided MaterialMasterDto
     * @param materialMasterDto The DTO containing material master information to be saved.
     * @return The saved MaterialMasterDto
     */
    @Override
    public MaterialMasterDto saveMaterials(MaterialMasterDto materialMasterDto) {
        // Convert dto to entity
        MaterialMaster material = new MaterialMaster();
        material.setMaterialName(materialMasterDto.getMaterialName());

        // Check if there exists a material with the given name
        if(materialMasterRepository.existsByMaterialName(materialMasterDto.getMaterialName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Material is already present.");
        }

        HttpSession session = httpServletRequest.getSession();
        String loggedInUser = session.getAttribute("userId").toString(); // Assuming the user creation is done by the current session user
        material.setMaterialCreatedBy(loggedInUser);
        material.setMaterialCreatedDate(LocalDateTime.now());
        material.setMaterialModifiedBy(loggedInUser);
        material.setMaterialModifiedDate(LocalDateTime.now());

        // Save materials
        MaterialMaster savedMaterial = materialMasterRepository.save(material);
        return modelMapper.map(savedMaterial, MaterialMasterDto.class);
    }

    /**
     * Get all materials as DTOs.
     * @return List of MaterialMasterDto objects representing materials.
     */
    @Override
    public List<MaterialMasterDto> getAllMaterials() {
        // Fetch all materials
        List<MaterialMaster> listOfMaterials = materialMasterRepository.findAll();

        return listOfMaterials.stream().map(materialMaster -> modelMapper.map(materialMaster, MaterialMasterDto.class)).collect(Collectors.toList());
    }

    /**
     * Get all material names.
     * @return List of strings containing material names.
     */
    @Override
    public List<String> getAllMaterialNames() {
        List<String> listOfMaterialNames = materialMasterRepository.findAllMaterialNameByMaterialStatus("ACTIVE");

        return listOfMaterialNames;
    }

    /**
     * Delete a material by its name.
     * @param materialName The ID of the material to be deleted.
     */
    @Override
    public void deleteMaterial(String materialName) {
        MaterialMaster materialMaster = materialMasterRepository.findByMaterialName(materialName);
        if (materialMaster.getMaterialStatus().equals("ACTIVE")){
            materialMaster.setMaterialStatus("INACTIVE");
        }
        materialMasterRepository.save(materialMaster);
    }
}
