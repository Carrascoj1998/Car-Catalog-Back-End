package com.astontech.RestFA.services.impl;

import com.astontech.RestFA.domain.Vehicle;
import com.astontech.RestFA.domain.VehicleDTO;
import com.astontech.RestFA.domain.VehicleMake;
import com.astontech.RestFA.domain.VehicleModel;
import com.astontech.RestFA.exceptions.FieldNotFoundException;
import com.astontech.RestFA.exceptions.VehicleMakeAlreadyInDB;
import com.astontech.RestFA.exceptions.VehicleMakeNotFoundException;
import com.astontech.RestFA.repositories.VehicleMakeRepo;
import com.astontech.RestFA.services.VehicleMakeService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
    public Iterable<VehicleDTO> getAllMake() {

        List<VehicleDTO> vehicleList = new ArrayList<>();
        for(VehicleMake vmak : vehicleMakeRepo.findAll()) {
            for(VehicleModel vmod : vmak.getVehicleModelList()) {
                for(Vehicle v : vmod.getVehicleList()) {
                    VehicleDTO newVehicleDTO = new VehicleDTO();
                    newVehicleDTO.setMake(vmak.getVehicleMake());
                    newVehicleDTO.setModel(vmod.getVehicleModel());
                    newVehicleDTO.setColor(v.getColor());
                    newVehicleDTO.setVin(v.getVin());
                    newVehicleDTO.setYear(v.getYear());
                    newVehicleDTO.setPurchasePrice(v.getPurchasePrice());
                    vehicleList.add(newVehicleDTO);

                }
            }
        }
        return vehicleList;
    }

    @Override
    public VehicleMake findByIdOrMake(String vehicleMake, Integer id) {
        return vehicleMakeRepo.findByVehicleMakeOrId(vehicleMake, id)
                .orElseThrow(()-> new VehicleMakeNotFoundException( vehicleMake == null? String.valueOf(id): vehicleMake));
    }

    @Override
    public VehicleMake saveMake(VehicleDTO vehicleDTO) throws VehicleMakeAlreadyInDB {
//        String checkVehicleMake = vehicleDTO.getVehicleMake();

//        for(VehicleMake newVehicleMake: vehicleMakeRepo.findAll()){
//            if (newVehicleMake.getVehicleMake().equals(checkVehicleMake)){
//                throw new VehicleMakeAlreadyInDB(checkVehicleMake);
//            }
//        }

        System.out.println(vehicleDTO.getMake());

        System.out.println(vehicleDTO.getModel());

        VehicleMake vehicleMake = new VehicleMake();
        VehicleModel vehicleModel = new VehicleModel();
        Vehicle vehicle = new Vehicle();
        List<Vehicle> vehicleList = new ArrayList<>();
        List<VehicleModel> vehicleModelList = new ArrayList<>();



        vehicleMake.setVehicleMake(vehicleDTO.getMake());
        vehicleModel.setVehicleModel(vehicleDTO.getModel());
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setVin(vehicleDTO.getVin());
        vehicle.setPurchasePrice(vehicleDTO.getPurchasePrice());


//        vehicle.setVehicleModel(vehicleDTO.getModel());
//        vehicle.setVehicleMake(vehicleDTO.getMake());

        vehicleModelList.add(vehicleModel);
        vehicleList.add(vehicle);

        vehicleMake.setVehicleModelList(vehicleModelList);
        vehicleModel.setVehicleList(vehicleList);


        return vehicleMakeRepo.save(vehicleMake);
    }

    @Override
    public VehicleMake saveMake(VehicleMake vehicleMake) {
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
