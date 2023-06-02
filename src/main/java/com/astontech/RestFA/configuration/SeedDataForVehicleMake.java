package com.astontech.RestFA.configuration;


import com.astontech.RestFA.domain.VehicleMake;
import com.astontech.RestFA.domain.VehicleModel;
import com.astontech.RestFA.repositories.VehicleMakeRepo;
import com.astontech.RestFA.repositories.VehicleModelRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SeedDataForVehicleMake implements CommandLineRunner {

    private final VehicleMakeRepo vehicleMakeRepo;
    private final VehicleModelRepo vehicleModelRepo;

    public SeedDataForVehicleMake(VehicleMakeRepo vehicleMakeRepo, VehicleModelRepo vehicleModelRepo){
        this.vehicleMakeRepo = vehicleMakeRepo;
        this.vehicleModelRepo = vehicleModelRepo;
    }


    @Override
    public void run(String... args) throws Exception {
        if(vehicleMakeRepo.count()== 0){
//
//            VehicleMake vehicleMake1 = new VehicleMake("Kia");
//            VehicleMake vehicleMake2 = new VehicleMake("Dodge");
//            VehicleMake vehicleMake3 = new VehicleMake("BMW");
//            VehicleMake vehicleMake4 = new VehicleMake("IDK");
//            VehicleMake vehicleMake5 = new VehicleMake("Audi");
//            VehicleMake vehicleMake6 = new VehicleMake("Ford");
//
//
//
//
//
//
//            vehicleMakeRepo.save(vehicleMake1);
//            vehicleMakeRepo.save(vehicleMake2);
//            vehicleMakeRepo.save(vehicleMake3);
//            vehicleMakeRepo.save(vehicleMake4);
//            vehicleMakeRepo.save(vehicleMake5);
//            vehicleMakeRepo.save(vehicleMake6);


        }
    }
}
