package com.astontech.RestFA.repositories;

import com.astontech.RestFA.domain.VehicleMake;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VehicleMakeRepo extends CrudRepository<VehicleMake, Integer> {


    VehicleMake findVehicleMakeById(Integer id);

    Optional<VehicleMake> findByVehicleMake(String vehicleMake);

    Optional<VehicleMake> findByVehicleMakeOrId(String vehicleMake, Integer id);






}
