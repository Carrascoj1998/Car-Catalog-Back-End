package com.astontech.RestFA.services;

import com.astontech.RestFA.domain.VehicleDTO;
import com.astontech.RestFA.domain.VehicleMake;


import java.util.Map;
import java.util.Optional;


public interface VehicleMakeService {


    Optional<VehicleMake> findVehicleMakeById(Integer id);

    Optional<VehicleMake> findByVehicleMake(String vehicleMake);

    Iterable<VehicleDTO> getAllMake();

    VehicleMake patchVehicleMake(Map<String, Object> updates, Integer id);

    VehicleMake findByIdOrMake(String vehicleMake, Integer id);

    VehicleMake saveMake(VehicleDTO vehicleDTO);

    VehicleMake saveMake(VehicleMake vehicleMake);

    Iterable<VehicleMake> saveMakeList(Iterable<VehicleMake> vehicleMakeIterable);

    void deleteMakeById(Integer id);

    void deleteMake(VehicleMake vehicleMake);



}
