package com.weighbridge.services;

public interface AdminHomeService {

    long findNoOfActiveUsers();

    long findNoOfInActiveUsers();

    long findNoOfRegisteredVehicle();
}
