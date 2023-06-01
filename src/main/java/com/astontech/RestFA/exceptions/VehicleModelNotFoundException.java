package com.astontech.RestFA.exceptions;

public class VehicleModelNotFoundException extends RuntimeException {

    public VehicleModelNotFoundException(Integer id){
        super("Could Not Find Vehicle Model: " + id);

    }

    public VehicleModelNotFoundException(String vehicleModel){
        super("Could Not Find Vehicle Model: " + vehicleModel);

    }

}
