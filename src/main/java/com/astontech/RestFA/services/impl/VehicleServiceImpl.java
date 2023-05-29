package com.astontech.RestFA.services.impl;

import com.astontech.RestFA.domain.Vehicle;
import com.astontech.RestFA.repositories.VehicleRepo;
import com.astontech.RestFA.services.VehicleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepo vehicleRepo;

    public VehicleServiceImpl(VehicleRepo vehicleRepo){
        this.vehicleRepo = vehicleRepo;
    }


    @Override
    public Vehicle getVehicleById(Integer id) {
        return vehicleRepo.getVehicleById(id);
    }

    @Override
    public Iterable<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    @Override
    public void deleteVehicleById(Integer id) {
        vehicleRepo.deleteById(id);
    }

    @Override
    public void deleteVehicle(Vehicle vehicle) {
        vehicleRepo.delete(vehicle);
    }

    @Override
    public Vehicle findByIdOrPurchacePrice(Integer id, String purchasePrice) {
        return null;
    }

    @Override
    public Optional<Vehicle> findByPurchasePrice(String purchasePrice) {
        return Optional.empty();
    }
}

