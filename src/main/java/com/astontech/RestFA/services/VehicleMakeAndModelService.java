package com.astontech.RestFA.services;

import com.astontech.RestFA.domain.VehicleMakeAndModel;
import com.astontech.RestFA.services.impl.VehicleMakeServiceAndModelImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface VehicleMakeAndModelService {

    Optional<VehicleMakeAndModel> findVehicleMakeAndModelById(Integer id);

    Optional<VehicleMakeAndModel> findByVehicleMake(String vehicleMake);

    Iterable<VehicleMakeAndModel> getAllMakeAndModel();

    VehicleMakeAndModel findByIdOrMake(String vehicleMake, Integer id);

    VehicleMakeAndModel saveMakeAndModel(VehicleMakeAndModel vehicleMakeAndModel);

    Iterable<VehicleMakeAndModel> saveMakeAndModelList(Iterable<VehicleMakeAndModel> vehicleMakeAndModelIterable);

    void deleteMakAndModelById(Integer id);

    void deleteMakeAndModel(VehicleMakeAndModel vehicleMakeAndModel);



}
