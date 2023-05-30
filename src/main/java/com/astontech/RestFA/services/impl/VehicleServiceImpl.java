package com.astontech.RestFA.services.impl;

import com.astontech.RestFA.domain.Vehicle;
import com.astontech.RestFA.exceptions.FieldNotFoundException;
import com.astontech.RestFA.exceptions.VehicleNotFoundException;
import com.astontech.RestFA.repositories.VehicleRepo;
import com.astontech.RestFA.services.VehicleService;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepo vehicleRepo;

    public VehicleServiceImpl(VehicleRepo vehicleRepo){
        this.vehicleRepo = vehicleRepo;
    }

    @Override
    public Optional<Vehicle> getVehicleById(Integer id) {
        return vehicleRepo.findById(id);
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
        return vehicleRepo.findByPurchasePrice(purchasePrice);
    }

    @Override
    public Vehicle patchVehicle(Map<String, Object> updates, Integer id) throws FieldNotFoundException {
        //find Vehicle By id or throw exception
        Vehicle vehicleToPatch = vehicleRepo.findByPurchasePriceOrId(id, null)
                .orElseThrow(() -> new VehicleNotFoundException(id));

        //Iterate over map of fields to update
        updates.forEach((k, o) ->{
            System.out.println(k + ":" + o);

            //use reflection to get the accessor for field
            try {
                Field nameField = vehicleToPatch.getClass().getDeclaredField(k);
                //set field to value
                nameField.setAccessible(true);
                nameField.set(vehicleToPatch, o);

                //handle cpl exception
            } catch (NoSuchFieldException | IllegalAccessException  e) {
                System.out.println("No such field: " + k);
                throw new FieldNotFoundException(k);
            }
        });

        //save the vehicle we found
        return vehicleRepo.save(vehicleToPatch);
    }
}

