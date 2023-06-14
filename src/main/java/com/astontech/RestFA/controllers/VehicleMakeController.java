package com.astontech.RestFA.controllers;


import com.astontech.RestFA.domain.VehicleDTO;
import com.astontech.RestFA.domain.VehicleMake;
import com.astontech.RestFA.exceptions.VehicleMakeNotFoundException;
import com.astontech.RestFA.services.VehicleMakeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/vehicleMake")
@Slf4j
public class VehicleMakeController {

    //regionDI

    private final VehicleMakeService vehicleMakeService;

    public VehicleMakeController(VehicleMakeService vehicleMakeService){
        this.vehicleMakeService = vehicleMakeService;
    }

    //endregion

    @GetMapping()
    public Optional<VehicleMake> getByIdOrMakeName(@RequestParam(required = false) Integer id,
                             @RequestParam(required = false) String vehicleMake){

        if(vehicleMake != null && !vehicleMake.isEmpty()){
            return Optional.ofNullable(vehicleMakeService.findByVehicleMake(vehicleMake)
                   .orElseThrow(()-> new VehicleMakeNotFoundException(vehicleMake)));
        }else if( id != null){
            return vehicleMakeService.findVehicleMakeById(id);
        }else
            throw new VehicleMakeNotFoundException((vehicleMake == null ? id: Integer.valueOf(vehicleMake)));
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<VehicleMake>> findAllMakes(){
        Iterable<VehicleMake> vehicleMakeIterable = vehicleMakeService.getAllMake();
        return new ResponseEntity<>(vehicleMakeIterable,
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "vehicleMakeCache" , key= "#id")
    public ResponseEntity<VehicleMake> getVehicleMakeById(@PathVariable Integer id) throws VehicleMakeNotFoundException {
        VehicleMake vehicleMake = vehicleMakeService.findVehicleMakeById(id)
                .orElseThrow(()-> new VehicleMakeNotFoundException(id));
        return new ResponseEntity<>(vehicleMake, HttpStatus.ACCEPTED);
    }

    @PostMapping("/")
    public ResponseEntity<VehicleMake> addVehicleMake(@RequestBody VehicleDTO vehicleDTO){
        return new ResponseEntity<>(vehicleMakeService.saveMake(vehicleDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    @CacheEvict(value = "vehicleMakeCache", key= "#vehicleMake.id")
    public ResponseEntity<VehicleMake> updateVehicleMake(@RequestBody VehicleMake vehicleMake){
        return new ResponseEntity<>(vehicleMakeService.saveMake(vehicleMake),
                HttpStatus.ACCEPTED
        );
    }

    @PatchMapping("/{id}")
    @CacheEvict(value = "vehicleMakeCache" , key= "#id")
    public ResponseEntity<VehicleMake> partialUpdate(@RequestBody Map<String, Object> updates,
                                                     @PathVariable Integer id){
        return new ResponseEntity<>(vehicleMakeService.patchVehicleMake(updates, id),
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "vehicleMakeCache" , key= "#id")
    public void deleteVehicleMake(@PathVariable Integer id){
        vehicleMakeService.deleteMakeById(id);
    }

    @DeleteMapping("/")
    @CacheEvict(value = "vehicleMakeCache" , key= "#vehicleMake.id")
    public void deleteVehicleMake1(@RequestBody VehicleMake vehicleMake){
        vehicleMakeService.deleteMake(vehicleMake);
    }





}
