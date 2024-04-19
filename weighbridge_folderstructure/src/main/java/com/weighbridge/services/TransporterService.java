package com.weighbridge.services;

import com.weighbridge.payloads.TransporterRequest;

import java.util.List;

public interface TransporterService {
    public String addTransporter(TransporterRequest transporterRequest);

    public List<String> getAllTransporter();
}
