package com.weighbridge.controllers;

import com.weighbridge.dtos.MaterialMasterDto;
import com.weighbridge.services.MaterialMasterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This Controller class for managing material master data.
 */
@RestController
@RequestMapping("/api/v1/materials")
public class MaterialMasterController {

    private MaterialMasterService materialMasterService;

    /**
     * Constructor for MaterialMasterController.
     * @param materialMasterService The service to handle material master operations.
     */
    public MaterialMasterController(MaterialMasterService materialMasterService) {
        this.materialMasterService = materialMasterService;
    }

    /**
     * Endpoint for saving materials
     * @param materialMasterDto The DTO containing material information to be saved.
     * @return ResponseEntity containing the saved MaterialMasterDto with HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<MaterialMasterDto> saveMaterials(@RequestBody MaterialMasterDto materialMasterDto){
        MaterialMasterDto savedMaterial = materialMasterService.saveMaterials(materialMasterDto);
        return new ResponseEntity<>(savedMaterial, HttpStatus.CREATED);
    }


    /**
     * Endpoint for retrieving all materials
     * @return ResponseEntity containing a list of all MaterialMasterDto with HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<List<MaterialMasterDto>> getAllMaterials(){
        List<MaterialMasterDto> allMaterials = materialMasterService.getAllMaterials();
        return ResponseEntity.ok(allMaterials);
    }

    /**
     * Endpoint for retrieving all material names.
     * @return ResponseEntity containing a list of all material names with HTTP status OK.
     */
    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllMaterialNames(){
        List<String> allMaterialNames = materialMasterService.getAllMaterialNames();
        return ResponseEntity.ok(allMaterialNames);
    }

    /**
     * Endpoint for deleting a material by its name.
     * @param materialName The name of the material to be deleted.
     * @return ResponseEntity wit a success message indicating the material is deleted successfully.
     */
    @DeleteMapping("/{materialName}")
    public ResponseEntity<String> deleteMaterial(@PathVariable String materialName){
        materialMasterService.deleteMaterial(materialName);
        return ResponseEntity.ok("Material is deleted successfully");
    }

}
