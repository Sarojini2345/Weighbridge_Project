package com.weighbridge.services;

import com.weighbridge.dtos.SupplierMasterDto;

import java.util.List;

public interface SupplierMasterService {

    SupplierMasterDto createSupplier(SupplierMasterDto supplierMasterDto);

    List<SupplierMasterDto> getAllSupplier();
    List<String> getAllSupplierAsString();
}
