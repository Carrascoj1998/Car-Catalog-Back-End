package com.astontech.RestFA.controllers;

import com.astontech.RestFA.domain.VehicleModel;
import com.astontech.RestFA.exceptions.VehicleModelNotFoundException;
import com.astontech.RestFA.services.VehicleModelService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/vehicleModel")
public class VehicleModelController {


    //regionDI

    private final VehicleModelService vehicleModelService;

    private VehicleModelController(VehicleModelService vehicleModelService){
        this.vehicleModelService= vehicleModelService;
    }
    //endregion

    @GetMapping("/")
    public ResponseEntity<Iterable<VehicleModel>> getAllVehicleModels(){
        Iterable<VehicleModel> vehicleModelIterable = vehicleModelService.getAllVehicleModels();
        System.out.println(vehicleModelIterable);
        return new ResponseEntity<>(vehicleModelIterable, HttpStatus.ACCEPTED);
    }

    @GetMapping("{id}")
//    @Cacheable(value = "vehicleModelCache" , key= "#id")
    public ResponseEntity<VehicleModel> getVehicleModelById(@PathVariable Integer id) throws VehicleModelNotFoundException{
        VehicleModel vehicleModel = vehicleModelService.findVehicleModelById(id)
                .orElseThrow(()-> new VehicleModelNotFoundException(id));
        return new ResponseEntity<>(vehicleModel, HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public Optional<VehicleModel> getByIdOrPurchasePrice(@RequestParam(required = false) Integer id,
                                                         @RequestParam(required = false)String vehicleModel){

        if(vehicleModel != null && !vehicleModel.isEmpty()){
            return Optional.ofNullable(vehicleModelService.findByModel(vehicleModel))
                    .orElseThrow(()-> new VehicleModelNotFoundException(vehicleModel));
        }else if(id != null){
            return vehicleModelService.findVehicleModelById(id);
        }else{
            throw new VehicleModelNotFoundException((vehicleModel == null? id: Integer.valueOf(vehicleModel)));
        }


    }

    @PatchMapping("/{id}")
//    @Cacheable(value = "vehicleModelCache" , key= "#id")
    public ResponseEntity<VehicleModel> partialUpdate(@RequestBody Map<String, Object>updates,
                                                      @PathVariable Integer id){
        return new ResponseEntity<>(vehicleModelService.patchVehicleModel(updates, id),
                HttpStatus.ACCEPTED);
    }

    @PostMapping("/")
    public ResponseEntity<VehicleModel> addVehicleModel(@RequestBody VehicleModel vehicleModel){
        return new ResponseEntity<>(vehicleModelService.saveVehicleModel(vehicleModel),
                HttpStatus.ACCEPTED);
    }

    @PutMapping("/")
//    @CacheEvict(value = "vehicleModelCache", key= "#vehicleModel.id")
    public ResponseEntity<VehicleModel> updateVehicleModel(@RequestBody VehicleModel vehicleModel){
        return new ResponseEntity<>(vehicleModelService.saveVehicleModel(vehicleModel),
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
//    @CacheEvict(value = "vehicleModelCache", key= "#id")
    public void deleteVehicleModelById(@PathVariable Integer id){
        vehicleModelService.deleteVehicleModelById(id);

    }

    @DeleteMapping("/")
//    @CacheEvict(value = "vehicleModelCache", key= "#vehicleModel.id")
    public void deleteVehicleModel(@RequestBody VehicleModel vehicleModel){
        vehicleModelService.deleteVehicleModel(vehicleModel);
    }











}
