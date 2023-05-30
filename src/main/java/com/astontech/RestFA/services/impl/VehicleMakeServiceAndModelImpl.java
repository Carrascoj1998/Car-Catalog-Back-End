package com.astontech.RestFA.services.impl;

import com.astontech.RestFA.domain.VehicleMakeAndModel;
import com.astontech.RestFA.exceptions.VehicleNotFoundException;
import com.astontech.RestFA.repositories.VehicleMakeAndModelRepo;
import com.astontech.RestFA.services.VehicleMakeAndModelService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleMakeServiceAndModelImpl implements VehicleMakeAndModelService {


    //regionDI
    private VehicleMakeAndModelRepo vehicleMakeAndModelRepo;

    public VehicleMakeServiceAndModelImpl(VehicleMakeAndModelRepo vehicleMakeAndModelRepo){
        this.vehicleMakeAndModelRepo = vehicleMakeAndModelRepo;
    }

    //endregion


    @Override
    public Optional<VehicleMakeAndModel> findVehicleMakeAndModelById(Integer id) {
        return vehicleMakeAndModelRepo.findById(id);
    }

    @Override
    public Optional<VehicleMakeAndModel> findByVehicleMake(String vehicleMake) {
        return vehicleMakeAndModelRepo.findByVehicleMake(vehicleMake);
    }

    @Override
    public Iterable<VehicleMakeAndModel> getAllMakeAndModel() {
        return vehicleMakeAndModelRepo.findAll();
    }

    @Override
    public VehicleMakeAndModel findByIdOrMake(String vehicleMake, Integer id) {
        return  vehicleMakeAndModelRepo.findByMakeOrId(vehicleMake, id)
                .orElseThrow(() -> new VehicleNotFoundException(vehicleMake == null ?
                        String.valueOf(id): vehicleMake));
    }

    @Override
    public VehicleMakeAndModel saveMakeAndModel(VehicleMakeAndModel vehicleMakeAndModel) {
        return vehicleMakeAndModelRepo.save(vehicleMakeAndModel);
    }

    @Override
    public Iterable<VehicleMakeAndModel> saveMakeAndModelList(Iterable<VehicleMakeAndModel> vehicleMakeAndModelIterable) {
        return vehicleMakeAndModelRepo.saveAll(vehicleMakeAndModelIterable);
    }

    @Override
    public void deleteMakAndModelById(Integer id) {
        vehicleMakeAndModelRepo.deleteById(id);
    }

    @Override
    public void deleteMakeAndModel(VehicleMakeAndModel vehicleMakeAndModel) {
        vehicleMakeAndModelRepo.delete(vehicleMakeAndModel);

    }
}
