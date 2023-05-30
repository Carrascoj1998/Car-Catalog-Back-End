package com.astontech.RestFA.repositories;

import com.astontech.RestFA.domain.VehicleMakeAndModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VehicleMakeAndModelRepo extends CrudRepository<VehicleMakeAndModel, Integer> {


    VehicleMakeAndModel findVehicleMakeAndModelById(Integer id);

    Optional<VehicleMakeAndModel> findByVehicleMake(String vehicleMake);

    Optional<VehicleMakeAndModel> findByMakeOrId(String vehicleMake, Integer id);


}
