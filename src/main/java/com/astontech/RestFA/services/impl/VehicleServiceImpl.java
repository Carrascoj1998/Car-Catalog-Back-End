package com.astontech.RestFA.services.impl;

import com.astontech.RestFA.domain.Vehicle;
import com.astontech.RestFA.domain.VehicleDTO;
import com.astontech.RestFA.domain.VehicleMake;
import com.astontech.RestFA.domain.VehicleModel;
import com.astontech.RestFA.exceptions.FieldNotFoundException;
import com.astontech.RestFA.exceptions.VehicleNotFoundException;
import com.astontech.RestFA.exceptions.VinAlreadyInDB;
import com.astontech.RestFA.repositories.VehicleMakeRepo;
import com.astontech.RestFA.repositories.VehicleModelRepo;
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
    private final VehicleModelRepo vehicleModelRepo;
    private final VehicleMakeRepo vehicleMakeRepo;

    public VehicleServiceImpl(VehicleRepo vehicleRepo, VehicleModelRepo vehicleModelRepo, VehicleMakeRepo vehicleMakeRepo) {
        this.vehicleRepo = vehicleRepo;
        this.vehicleModelRepo = vehicleModelRepo;
        this.vehicleMakeRepo = vehicleMakeRepo;
    }

    @Override
    public Optional<Vehicle> getVehicleById(Integer id) {
        return vehicleRepo.findById(id);
    }

    @Override
    public Iterable<Vehicle> getAllVehicles() {
        for( Vehicle v: vehicleRepo.findAll()){
            System.out.println(v);
        }
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
        System.out.println();

        return vehicleRepo.save(vehicle);

    }

    @Override
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
    public void deleteVehicleById(Integer id) {
        vehicleRepo.deleteById(id);
    }

    @Override

    public void deleteVehicle(Vehicle vehicle) {
        vehicleRepo.delete(vehicle);
    }


    @Override
    public void deleteByVin(String vin) {
        VehicleModel vm = vehicleModelRepo.findVehicleModelByVehicleVin(vin);
        Vehicle vehicle = vehicleRepo.findByVin(vin);
        vm.getVehicleList().remove(vehicle);
        vehicleModelRepo.save(vm);
        vehicleRepo.deleteByVin(vin);
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
    public Vehicle patchVehicle(Map<String, Object> updates, String vin)  {
        //find Vehicle By id or throw exception
        Vehicle vehicleToPatch = vehicleRepo.findByVin(vin);


        String newMakeName = "";
        String newModelName = "";

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


//    @Override
//    public Vehicle patchVehicle(Map<String, Object> updates, String vin) {
//        // Find Vehicle by VIN or throw an exception
//        Vehicle vehicleToPatch = vehicleRepo.findAllByVin(vin);
//
//        // Get the existing make and model
//        String existingMake = null;
//        String existingModel = null;
//
//        // Find the existing make and model for the vehicle
//        for (VehicleMake vm : vehicleMakeRepo.findAll()) {
//            for (VehicleModel vmod : vm.getVehicleModelList()) {
//                if (vmod.getVehicleList().contains(vehicleToPatch)) {
//                    existingMake = vm.getVehicleMake();
//                    existingModel = vmod.getVehicleModel();
//                    break;
//                }
//            }
//            if (existingMake != null && existingModel != null) {
//                break;
//            }
//        }
//
//        // Iterate over the map of fields to update
//        for (Map.Entry<String, Object> entry : updates.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//            System.out.println(key + ":" + value);
//
//            if (key.equals("vehicleMake")) {
//                existingMake = value.toString();
//            }
//
//            if (key.equals("vehicleModel")) {
//                existingModel = value.toString();
//            }
//        }
//
//        // Update the make and model in the data structure
//        boolean updated = false;
//
//        for (VehicleMake vm : vehicleMakeRepo.findAll()) {
//            for (VehicleModel vmod : vm.getVehicleModelList()) {
//                if (vmod.getVehicleModel().equals(existingModel)) {
//                    if (!vmod.getVehicleList().contains(vehicleToPatch)) {
//                        vmod.getVehicleList().add(vehicleToPatch);
//                        updated = true;
//                        break;
//                    }
//                } else {
//                    vmod.getVehicleList().remove(vehicleToPatch);
//                }
//            }
//            if (updated) {
//                break;
//            }
//        }
//
//        // Save the updated vehicle
//        return vehicleRepo.save(vehicleToPatch);
//    }





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

