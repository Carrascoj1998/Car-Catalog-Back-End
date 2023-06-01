package com.astontech.RestFA.services;

import com.astontech.RestFA.domain.VehicleModel;

import java.util.Map;
import java.util.Optional;

public interface VehicleModelService {


    Optional<VehicleModel> findVehicleModelById(Integer id);

    Optional<VehicleModel> findByModel(String vehicleModel);

    VehicleModel findByVehicleMakeOrId(String vehicleModel, Integer id);

    Iterable<VehicleModel> getAllVehicleModels();

    VehicleModel patchVehicleModel(Map<String , Object> updates, Integer id);

    VehicleModel saveVehicleModel(VehicleModel vehicleModel);

    Iterable<VehicleModel> saveVehicleModelList(Iterable<VehicleModel> vehicleModelIterable);

    void deleteVehicleModelById(Integer id);

    void deleteVehicleModel(VehicleModel vehicleModel);

}
