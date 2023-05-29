package com.astontech.RestFA.controllers;

import com.astontech.RestFA.domain.Vehicle;
import com.astontech.RestFA.repositories.VehicleRepo;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/vehicle")
public class VehicleRestController {

    //region DI
    private VehicleRepo vehicleRepo;

    public VehicleRestController(VehicleRepo vehicleRepo){
        this.vehicleRepo = vehicleRepo;
    }

//    private VehicleService vehicleService;
//
//    public VehicleRestController(VehicleService vehicleService) {
//        this.vehicleService = vehicleService;
//    }

    //endregion


    //regionCRUD MAPPINGS
    @GetMapping("/")
    public Iterable<Vehicle> getAllVehicles(){
        return vehicleRepo.findAll();
    }

    @PostMapping("/")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle){
        return vehicleRepo.save(vehicle);
    }

    @PutMapping("/")
    public Vehicle updateVehicle(@RequestBody Vehicle vehicle){
        return vehicleRepo.save(vehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicleById(@PathVariable Integer id){
         vehicleRepo.deleteById(id);
    }

    //endregion

    //regionQUEry Param
    @GetMapping()//http://localhost:8080/vehicle?purchasePrice=$21,000
    public Optional<Vehicle> getByIdPurchasePrice(@RequestParam(required = false)Integer id,
                                                  @RequestParam(required = false)String purchasePrice){
        if(purchasePrice != null && !purchasePrice.isEmpty()){
            return vehicleRepo.findByPurchasePrice(purchasePrice);
        }else if (id != null){
            return Optional.of(vehicleRepo.findById(id).get());
        }else{
            //throw custom exception
            return Optional.of(new Vehicle());
        }
    }
    //region




}
