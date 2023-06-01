package com.astontech.RestFA.services.impl;

import com.astontech.RestFA.domain.Vehicle;
import com.astontech.RestFA.exceptions.FieldNotFoundException;
import com.astontech.RestFA.exceptions.VehicleNotFoundException;
import com.astontech.RestFA.exceptions.VinAlreadyInDB;
import com.astontech.RestFA.repositories.VehicleRepo;
import com.astontech.RestFA.services.VehicleService;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
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
    @Cacheable(value = "vehicleCache" , key= "#id")
    public Optional<Vehicle> getVehicleById(Integer id) {
        return vehicleRepo.findById(id);
    }

    @Override
    public Iterable<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) throws VinAlreadyInDB{
        String newVehicleVin = vehicle.getVin();

        for(Vehicle exitingVehicleVin: vehicleRepo.findAll()){
            if(exitingVehicleVin.getVin().equals(newVehicleVin)){
                System.out.println("Vehicle vin is already in the DataBase: " + newVehicleVin);
                throw  new VinAlreadyInDB(newVehicleVin);

            }
        }

        return vehicleRepo.save(vehicle);

    }

    @Override
    @CacheEvict(value = "vehicleCache", key= "#vehicle.id")
    public Vehicle updateVehicle(Vehicle vehicle) {

        String newVehicleVin1 = vehicle.getVin();

        for(Vehicle exitingVehicleVin: vehicleRepo.findAll()){
            if(exitingVehicleVin.getVin().equals(newVehicleVin1)){
                System.out.println("Vehicle vin is already in the DataBase: " + newVehicleVin1);
                throw  new VinAlreadyInDB(newVehicleVin1);

            }
        }


        return vehicleRepo.save(vehicle);
    }

    @Override
    @CacheEvict(value = "vehicleCache", key= "#id")
    public void deleteVehicleById(Integer id) {
        vehicleRepo.deleteById(id);
    }

    @Override
    @CacheEvict(value = "vehicleCache", key= "#vehicle.id")
    public void deleteVehicle(Vehicle vehicle) {
        vehicleRepo.delete(vehicle);
    }

    @Override
    @Cacheable(value = "vehicleCache", key = "#id")
    public Vehicle findByIdOrPurchasePrice(Integer id, String purchasePrice) throws VehicleNotFoundException{
        return vehicleRepo.findByPurchasePriceOrId(purchasePrice, id)
                .orElseThrow(()-> new VehicleNotFoundException(purchasePrice == null ? String.valueOf(id): purchasePrice));
    }

    @Override
    public Optional<Vehicle> findByPurchasePrice(String purchasePrice) {
        return vehicleRepo.findByPurchasePrice(purchasePrice);
    }

    @Override
    @CacheEvict(value = "vehicleCache", key= "#id")
    public Vehicle patchVehicle(Map<String, Object> updates, Integer id) throws FieldNotFoundException {
        //find Vehicle By id or throw exception
        Vehicle vehicleToPatch = vehicleRepo.findByPurchasePriceOrId(null, id)
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


    private Boolean isDuplicate(Vehicle vehicle){
        String newVehicleVin = vehicle.getVin();

        for(Vehicle exitingVehicleVin: vehicleRepo.findAll()){
            if(exitingVehicleVin.getVin().equals(newVehicleVin)){
                System.out.println("Vehicle vin is already in the DataBase: " + newVehicleVin);

                return true;
            }
        }

        return false;



    }



}

