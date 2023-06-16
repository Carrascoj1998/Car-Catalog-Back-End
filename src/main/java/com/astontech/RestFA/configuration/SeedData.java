package com.astontech.RestFA.configuration;

import com.astontech.RestFA.domain.Vehicle;
import com.astontech.RestFA.repositories.VehicleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class SeedData implements CommandLineRunner {

    //regionDI
    private VehicleRepo vehicleRepo;

    public SeedData(VehicleRepo vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }

    //endregion

    @Override
    public void run(String... args) throws Exception {
        if(vehicleRepo.count()== 0){

//
//                Vehicle vehicle1 = new Vehicle("2021", "Red", "$21,000", "1FMZU77KX4UA69977");
//                Vehicle vehicle2 = new Vehicle("2010", "Gray", "$15,090", "JTKJF5C72B3011634");
//                Vehicle vehicle3 = new Vehicle("2015", "Black", "$90,000", "2FABP7EV7BX127047");
//                Vehicle vehicle4 = new Vehicle("2001", "Yellow", "$14,999", "4T1BE32K85U528911");
//                Vehicle vehicle5 = new Vehicle("2023", "Black", "$25,999", "SCFBF03C19GC12573");
//                Vehicle vehicle6 = new Vehicle("2020", "White", "$25,000", "SAJWA2GEXBMV00832");
//
//
//                vehicleRepo.save(vehicle1);
//                vehicleRepo.save(vehicle2);
//                vehicleRepo.save(vehicle3);
//                vehicleRepo.save(vehicle4);
//                vehicleRepo.save(vehicle5);
//                vehicleRepo.save(vehicle6);

        }






    }
}
