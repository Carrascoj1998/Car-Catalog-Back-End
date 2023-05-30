package com.astontech.RestFA.configuration;

        import com.astontech.RestFA.domain.VehicleMakeAndModel;
        import com.astontech.RestFA.repositories.VehicleMakeAndModelRepo;
        import org.springframework.boot.CommandLineRunner;
        import org.springframework.stereotype.Component;

@Component
public class SeedDataForMakeAndModel implements CommandLineRunner {

    //regionDI
    private VehicleMakeAndModelRepo vehicleMakeAndModelRepo;

    public SeedDataForMakeAndModel(VehicleMakeAndModelRepo vehicleMakeAndModelRepo){
        this.vehicleMakeAndModelRepo = vehicleMakeAndModelRepo;
    }

    //endregion



    @Override
    public void run(String... args) throws Exception {
        if(vehicleMakeAndModelRepo.count()==0){
            VehicleMakeAndModel makeAndModel1 = new VehicleMakeAndModel("Kia", "Forte");
            VehicleMakeAndModel makeAndModel2 = new VehicleMakeAndModel("Dodge", "Durango");
            VehicleMakeAndModel makeAndModel3 = new VehicleMakeAndModel("Kia", "Rio");
            VehicleMakeAndModel makeAndModel4 = new VehicleMakeAndModel("Ford", "F-150");
            VehicleMakeAndModel makeAndModel5 = new VehicleMakeAndModel("Audi", "GXC");
            VehicleMakeAndModel makeAndModel6 = new VehicleMakeAndModel("BMW", "X6");

            vehicleMakeAndModelRepo.save(makeAndModel1);
            vehicleMakeAndModelRepo.save(makeAndModel2);
            vehicleMakeAndModelRepo.save(makeAndModel3);
            vehicleMakeAndModelRepo.save(makeAndModel4);
            vehicleMakeAndModelRepo.save(makeAndModel5);
            vehicleMakeAndModelRepo.save(makeAndModel6);


        }
    }
}
