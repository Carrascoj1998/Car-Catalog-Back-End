package com.astontech.RestFA.repositories;

import com.astontech.RestFA.domain.VehicleModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Map;
import java.util.Optional;

public interface VehicleModelRepo extends CrudRepository<VehicleModel , Integer> {


    VehicleModel findVehicleModelById(Integer id);

    Optional<VehicleModel> findByVehicleModel(String vehicleModel);

//    Optional<VehicleModel> findVehicleModel(String vehicleModel);

    Optional<VehicleModel> findByVehicleModelOrId(String vehicleModel, Integer id);


}
