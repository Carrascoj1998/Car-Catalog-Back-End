package com.astontech.RestFA.services.impl;



import com.astontech.RestFA.domain.VehicleModel;
import com.astontech.RestFA.exceptions.FieldNotFoundException;
import com.astontech.RestFA.exceptions.VehicleModelAndCreatDateInDBAlready;
import com.astontech.RestFA.exceptions.VehicleModelNotFoundException;
import com.astontech.RestFA.repositories.VehicleModelRepo;
import com.astontech.RestFA.services.VehicleModelService;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;
import java.lang.reflect.Field;

@Service
public class VehicleModelServiceImpl implements VehicleModelService {

    //regionDI

    private VehicleModelRepo vehicleModelRepo;

    public VehicleModelServiceImpl(VehicleModelRepo vehicleModelRepo){
        this.vehicleModelRepo = vehicleModelRepo;
    }

    //endregion

    @Override
    public Optional<VehicleModel> findVehicleModelById(Integer id) {
        return Optional.ofNullable(vehicleModelRepo.findVehicleModelById(id));
    }

    @Override
    public VehicleModel findByVehicleMakeOrId(String vehicleModel, Integer id) {
        return vehicleModelRepo.findByVehicleModelOrId(vehicleModel, id)
                .orElseThrow(()-> new VehicleModelNotFoundException(vehicleModel == null ? String.valueOf(id): vehicleModel));
    }

    @Override
    public Optional<VehicleModel> findByModel(String vehicleModel) {
        return vehicleModelRepo.findByVehicleModel(vehicleModel);
    }

    @Override
    public Iterable<VehicleModel> getAllVehicleModels() {
        return vehicleModelRepo.findAll();
    }

    @Override
    public VehicleModel patchVehicleModel(Map<String, Object> updates, Integer id) throws FieldNotFoundException {
       VehicleModel vehicleModelToPatch = vehicleModelRepo.findByVehicleModelOrId(null, id)
       .orElseThrow(()-> new VehicleModelNotFoundException(id));

       updates.forEach((k,o)->{
           try{
               Field nameField = vehicleModelToPatch.getClass().getDeclaredField(k);
               //set field to value
               nameField.setAccessible(true);
               nameField.set(vehicleModelToPatch, o);

               //handle cpl exception
           } catch (NoSuchFieldException | IllegalAccessException  e) {
               System.out.println("No such field: " + k);
               throw new FieldNotFoundException(k);
           }

       });
       return vehicleModelRepo.save(vehicleModelToPatch);

    }

    @Override
    public VehicleModel saveVehicleModel(VehicleModel vehicleModel) throws VehicleModelAndCreatDateInDBAlready {
//        String checkCreateDate = vehicleModel.getCreateDate();
//        String checkModel = vehicleModel.getVehicleModel();
//
//
//        for(VehicleModel existingVehicleModel: vehicleModelRepo.findAll()){
//            if (existingVehicleModel.getVehicleModel().equals(checkModel) &&
//                    existingVehicleModel.getCreateDate().equals(checkCreateDate)){
//
//                System.out.println("Vehicle Make and CreateDate Already In the DataBase");
//                throw new VehicleModelAndCreatDateInDBAlready(checkModel, checkCreateDate);
//
//            }
//        }

        return vehicleModelRepo.save(vehicleModel);
    }

    @Override
    public Iterable<VehicleModel> saveVehicleModelList(Iterable<VehicleModel> vehicleModelIterable) {
        return vehicleModelRepo.saveAll(vehicleModelIterable);
    }

    @Override
    public void deleteVehicleModelById(Integer id) {
        vehicleModelRepo.deleteById(id);
    }

    @Override
    public void deleteVehicleModel(VehicleModel vehicleModel) {
        vehicleModelRepo.delete(vehicleModel);

    }
}
