package com.astontech.RestFA.configuration;

import com.astontech.RestFA.domain.VehicleMake;
import com.astontech.RestFA.domain.VehicleModel;
import com.astontech.RestFA.repositories.VehicleMakeRepo;
import com.astontech.RestFA.repositories.VehicleModelRepo;
import com.astontech.RestFA.repositories.VehicleRepo;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

public class SeedDataForAll implements CommandLineRunner {

    private final VehicleMakeRepo vehicleMakeRepo;
    private final VehicleRepo vehicleRepo;
    private final VehicleModelRepo vehicleModelRepo;

    public SeedDataForAll(VehicleMakeRepo vehicleMakeRepo,VehicleRepo vehicleRepo, VehicleModelRepo vehicleModelRepo){
        this.vehicleMakeRepo = vehicleMakeRepo;
        this.vehicleModelRepo=vehicleModelRepo;
        this.vehicleRepo=vehicleRepo;
    }


    @Override
    public void run(String... args) throws Exception {
        if (vehicleMakeRepo.count() == 0) {

//            VehicleMake vehicleMake1 = new VehicleMake("Forte", "2018", Arrays.asList(
//                    new VehicleModel("Kia")
//            ));
//
//            VehicleMake vehicleMake2 = new VehicleMake("Durango", "2015", Arrays.asList(
//                    new VehicleModel("Dodge")
//
//            ));
//
//
//
//            // Add more VehicleMake objects as needed
//
//            vehicleMakeRepo.save(vehicleMake1);
//            vehicleMakeRepo.save(vehicleMake2);

            // Save other VehicleMake objects
        }
    }

}
