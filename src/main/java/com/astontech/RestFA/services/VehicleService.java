package com.astontech.RestFA.services;

import com.astontech.RestFA.domain.Vehicle;

import java.util.Map;
import java.util.Optional;

public interface VehicleService {

    Optional<Vehicle> getVehicleById(Integer id);


    Iterable<Vehicle> getAllVehicles();

    Vehicle findByIdOrPurchasePrice(Integer id, String purchasePrice);

    Vehicle updateVehicle(Vehicle vehicle);

    Vehicle saveVehicle(Vehicle vehicle);

    void deleteVehicleById(Integer id);

    void deleteVehicle(Vehicle vehicle);

    Optional<Vehicle> findByPurchasePrice(String purchasePrice);

    Vehicle patchVehicle(Map<String, Object> updates, String vin);

    void deleteByVin(String vin);





}
