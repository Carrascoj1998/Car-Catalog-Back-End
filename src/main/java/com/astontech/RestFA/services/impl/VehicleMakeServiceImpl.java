package com.astontech.RestFA.services.impl;

import com.astontech.RestFA.domain.VehicleMake;
import com.astontech.RestFA.exceptions.FieldNotFoundException;
import com.astontech.RestFA.exceptions.VehicleMakeAlreadyInDB;
import com.astontech.RestFA.exceptions.VehicleMakeNotFoundException;
import com.astontech.RestFA.repositories.VehicleMakeRepo;
import com.astontech.RestFA.services.VehicleMakeService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;


@Service
public class VehicleMakeServiceImpl implements VehicleMakeService {

    //regionDI

    private final VehicleMakeRepo vehicleMakeRepo;

    public VehicleMakeServiceImpl(VehicleMakeRepo vehicleMakeRepo ){

        this.vehicleMakeRepo= vehicleMakeRepo;
    }
    //endregion


    @Override
    public Optional<VehicleMake> findVehicleMakeById(Integer id) {
        return Optional.ofNullable(vehicleMakeRepo.findVehicleMakeById(id));
    }

    @Override
    public Optional<VehicleMake> findByVehicleMake(String vehicleMake) {
        return vehicleMakeRepo.findByVehicleMake(vehicleMake);
    }

    @Override
    public VehicleMake patchVehicleMake(Map<String, Object> updates, Integer id) throws FieldNotFoundException {
        VehicleMake vehicleMakeToPatch = vehicleMakeRepo.findByVehicleMakeOrId(null, id)
                .orElseThrow(()-> new VehicleMakeNotFoundException(id));

        updates.forEach((k, o)->{
            System.out.println(k + ":" + o);


            try {
                Field nameField = vehicleMakeToPatch.getClass().getDeclaredField(k);
                //set field to value
                nameField.setAccessible(true);
                nameField.set(vehicleMakeToPatch, o);

                //handle cpl exception
            } catch (NoSuchFieldException | IllegalAccessException  e) {
                System.out.println("No such field: " + k);
                throw new FieldNotFoundException(k);
            }

        });



        return vehicleMakeRepo.save(vehicleMakeToPatch);


    }

    @Override
    public Iterable<VehicleMake> getAllMake() {
        return vehicleMakeRepo.findAll();
    }

    @Override
    public VehicleMake findByIdOrMake(String vehicleMake, Integer id) {
        return vehicleMakeRepo.findByVehicleMakeOrId(vehicleMake, id)
                .orElseThrow(()-> new VehicleMakeNotFoundException( vehicleMake == null? String.valueOf(id): vehicleMake));
    }

    @Override
    public VehicleMake saveMake(VehicleMake vehicleMake) throws VehicleMakeAlreadyInDB {
        String checkVehicleMake = vehicleMake.getVehicleMake();

        for(VehicleMake newVehicleMake: vehicleMakeRepo.findAll()){
            if (newVehicleMake.getVehicleMake().equals(checkVehicleMake)){
                throw new VehicleMakeAlreadyInDB(checkVehicleMake);
            }
        }

        return vehicleMakeRepo.save(vehicleMake);
    }

    @Override
    public Iterable<VehicleMake> saveMakeList(Iterable<VehicleMake> vehicleMakeIterable) {
        return vehicleMakeRepo.saveAll(vehicleMakeIterable);
    }

    @Override
    public void deleteMakeById(Integer id) {
        vehicleMakeRepo.deleteById(id);
    }

    @Override
    public void deleteMake(VehicleMake vehicleMake) {
        vehicleMakeRepo.delete(vehicleMake);

    }


}
