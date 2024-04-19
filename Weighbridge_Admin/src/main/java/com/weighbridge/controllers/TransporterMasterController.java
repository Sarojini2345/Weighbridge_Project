package com.weighbridge.controllers;

import com.weighbridge.entities.TransporterMaster;
import com.weighbridge.payloads.TransporterRequest;
import com.weighbridge.services.TransporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transporter")
public class TransporterMasterController {
    @Autowired
    private TransporterService transporterService;

    @PostMapping("/add")
    public ResponseEntity<String> addTransporters(@RequestBody TransporterRequest transporterRequest){
        String transporter = transporterService.addTransporter(transporterRequest);
        return ResponseEntity.ok(transporter);
    }

    @GetMapping("/get")
    public ResponseEntity<List<String>> getAllTransporterName(){
        List<String> allTransporter = transporterService.getAllTransporter();
        return ResponseEntity.ok(allTransporter);
    }
}