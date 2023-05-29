package com.astontech.RestFA.services;

import com.astontech.RestFA.domain.Vehicle;

import java.util.Optional;

public interface VehicleService {

    Vehicle getVehicleById(Integer id);

    Iterable<Vehicle> getAllVehicles();

    Vehicle findByIdOrPurchacePrice(Integer id, String purchasePrice);

    Vehicle updateVehicle(Vehicle vehicle);

    Vehicle saveVehicle(Vehicle vehicle);

    void deleteVehicleById(Integer id);

    void deleteVehicle(Vehicle vehicle);

    Optional<Vehicle> findByPurchasePrice(String purchasePrice);



}
