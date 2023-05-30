package com.astontech.RestFA.exceptions;

public class VehicleMakeAndModelNotFoundException extends RuntimeException {



    public VehicleMakeAndModelNotFoundException(Integer id){
        super("Could Not Find Vehicle Make And Model: " + id);
    }

    public VehicleMakeAndModelNotFoundException(String vehicleMake){
        super("Could not find Vehicle Make And Model: " + vehicleMake);
    }

}
