package com.astontech.RestFA.repositories;

import com.astontech.RestFA.domain.VehicleMakeAndModel;
import org.springframework.data.repository.CrudRepository;

public interface VehicleMakeAndModelRepo extends CrudRepository<VehicleMakeAndModel, Integer> {


    VehicleMakeAndModel findVehicleMakeAndModelById(Integer id);


}
