package com.astontech.RestFA.configuration;

import com.astontech.RestFA.domain.Vehicle;
import com.astontech.RestFA.domain.VehicleMake;
import com.astontech.RestFA.domain.VehicleModel;
import com.astontech.RestFA.repositories.VehicleMakeRepo;
import com.astontech.RestFA.repositories.VehicleModelRepo;
import com.astontech.RestFA.repositories.VehicleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedDataForAll implements CommandLineRunner {

    private final VehicleMakeRepo vehicleMakeRepo;

    public SeedDataForAll(VehicleMakeRepo vehicleMakeRepo) {
        this.vehicleMakeRepo = vehicleMakeRepo;
    }

    @Override
    public void run(String... args) throws Exception {


        Vehicle vehicle1 = new Vehicle("2021", "Red", "$21,000", "1FMZU77KX4UA69977");
        Vehicle vehicle2 = new Vehicle("2010", "Gray", "$15,090", "JTKJF5C72B3011634");
        Vehicle vehicle3 = new Vehicle("2015", "Black", "$90,000", "2FABP7EV7BX127047");
        Vehicle vehicle4 = new Vehicle("2001", "Yellow", "$14,999", "4T1BE32K85U528911");
        Vehicle vehicle5 = new Vehicle("2023", "Black", "$25,999", "SCFBF03C19GC12573");
        Vehicle vehicle6 = new Vehicle("2020", "White", "$25,000", "SAJWA2GEXBMV00832");

        VehicleModel vehicleModel1 = new VehicleModel("Forte", "2019");
        VehicleModel vehicleModel2 = new VehicleModel("Durango", "2020");
        VehicleModel vehicleModel3 = new VehicleModel("X6", "2023");
        VehicleModel vehicleModel4 = new VehicleModel("Charger", "2015");
        VehicleModel vehicleModel5 = new VehicleModel("F-150", "2017");
        VehicleModel vehicleModel6 = new VehicleModel("ProMaster", "2010");

        List<Vehicle> vehicleList1 = new ArrayList<>();
        vehicleList1.add(vehicle1);
        vehicleList1.add(vehicle2);
        vehicleModel1.setVehicleList(vehicleList1);

        List<Vehicle> vehicleList2 = new ArrayList<>();
        vehicleList2.add(vehicle3);
        vehicleList2.add(vehicle4);
        vehicleModel2.setVehicleList(vehicleList2);

        List<Vehicle> vehicleList3 = new ArrayList<>();
        vehicleList3.add(vehicle5);
        vehicleList3.add(vehicle6);
        vehicleModel3.setVehicleList(vehicleList3);

        VehicleMake vehicleMake1 = new VehicleMake("Kia");
        VehicleMake vehicleMake2 = new VehicleMake("Dodge");
        VehicleMake vehicleMake3 = new VehicleMake("BMW");
        VehicleMake vehicleMake4 = new VehicleMake("IDK");
        VehicleMake vehicleMake5 = new VehicleMake("Audi");
        VehicleMake vehicleMake6 = new VehicleMake("Ford");

        List<VehicleModel> vehicleModelList1 = new ArrayList<>();
        vehicleModelList1.add(vehicleModel1);
        vehicleModelList1.add(vehicleModel2);
        vehicleMake1.setVehicleModelList(vehicleModelList1);
        List<VehicleModel> vehicleModelList2 = new ArrayList<>();
        vehicleModelList2.add(vehicleModel3);
        vehicleModelList2.add(vehicleModel4);
        vehicleMake2.setVehicleModelList(vehicleModelList2);
        List<VehicleModel> vehicleModelList3 = new ArrayList<>();
        vehicleModelList3.add(vehicleModel5);
        vehicleModelList3.add(vehicleModel6);
        vehicleMake3.setVehicleModelList(vehicleModelList3);


        vehicleMakeRepo.save(vehicleMake1);
        vehicleMakeRepo.save(vehicleMake2);
        vehicleMakeRepo.save(vehicleMake3);
        vehicleMakeRepo.save(vehicleMake4);
        vehicleMakeRepo.save(vehicleMake5);
        vehicleMakeRepo.save(vehicleMake6);
    }

}
