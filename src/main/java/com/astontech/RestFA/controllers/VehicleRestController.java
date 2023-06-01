package com.astontech.RestFA.controllers;

import com.astontech.RestFA.domain.Vehicle;
import com.astontech.RestFA.exceptions.VehicleNotFoundException;
import com.astontech.RestFA.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/vehicle")
public class VehicleRestController {

    //regionDI
    private VehicleService vehicleService;

    public VehicleRestController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    //endregion

    //regionCRUD MAPPINGS
    @GetMapping("/")
    public ResponseEntity <Iterable<Vehicle>> getAllVehicles(){
        Iterable<Vehicle> vehicleIterable = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicleIterable, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Vehicle> getVehicleById(@PathVariable Integer id) throws VehicleNotFoundException{
        Vehicle vehicle = vehicleService.getVehicleById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
        return new ResponseEntity<>(vehicle, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vehicle> partialUpdateDynamic(@RequestBody Map<String, Object> updates,
                                                  @PathVariable Integer id){
        return new ResponseEntity<>(vehicleService.patchVehicle(updates, id),
                                                    HttpStatus.ACCEPTED
        );

    }

    @PostMapping("/")
    public ResponseEntity <Vehicle> addVehicle(@RequestBody Vehicle vehicle){
        return new ResponseEntity<>(vehicleService.saveVehicle(vehicle),
                                    HttpStatus.CREATED
        );
    }

    //idempotent-multiple request will not change the system
    @PutMapping("/")
    public ResponseEntity <Vehicle> updateVehicle(@RequestBody Vehicle vehicle){
        return new ResponseEntity<>(vehicleService.saveVehicle(vehicle),
                                    HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicleById(@PathVariable Integer id){
         vehicleService.deleteVehicleById(id);
    }



    //endregion

    //regionQUERY Param
    @GetMapping()//http://localhost:8080/vehicle?purchasePrice=$21,000
    public Optional<Vehicle> getByIdOrPurchasePrice(@RequestParam(required = false)Integer id,
                                                  @RequestParam(required = false)String purchasePrice){
        if(purchasePrice != null && !purchasePrice.isEmpty()){
            return Optional.ofNullable(vehicleService.findByPurchasePrice(purchasePrice)
                    .orElseThrow(() -> new VehicleNotFoundException(purchasePrice)));
        }else if (id != null){
            return vehicleService.getVehicleById(id);
        }else{
            //throw custom exception
            throw new VehicleNotFoundException((purchasePrice == null ? id: Integer.valueOf(purchasePrice)));
        }
    }

    //endregion










}
