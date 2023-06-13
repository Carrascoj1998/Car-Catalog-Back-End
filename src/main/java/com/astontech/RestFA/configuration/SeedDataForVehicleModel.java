package com.astontech.RestFA.configuration;


import com.astontech.RestFA.domain.VehicleModel;
import com.astontech.RestFA.repositories.VehicleModelRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedDataForVehicleModel implements CommandLineRunner {

    private VehicleModelRepo vehicleModelRepo ;

    public SeedDataForVehicleModel(VehicleModelRepo vehicleModelRepo){
        this.vehicleModelRepo = vehicleModelRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if(vehicleModelRepo.count() == 0){

            VehicleModel vehicleModel1 = new VehicleModel("Forte");
            VehicleModel vehicleModel2 = new VehicleModel("Durango");
            VehicleModel vehicleModel3 = new VehicleModel("X6");
            VehicleModel vehicleModel4 = new VehicleModel("Charger");
            VehicleModel vehicleModel5 = new VehicleModel("F-150");
            VehicleModel vehicleModel6 = new VehicleModel("ProMaster");


            vehicleModelRepo.save(vehicleModel1);
            vehicleModelRepo.save(vehicleModel2);
            vehicleModelRepo.save(vehicleModel3);
            vehicleModelRepo.save(vehicleModel4);
            vehicleModelRepo.save(vehicleModel5);
            vehicleModelRepo.save(vehicleModel6);


        }
    }
}
