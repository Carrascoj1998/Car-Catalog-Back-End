package com.astontech.RestFA.exceptions;

public class VehicleMakeNotFoundException extends RuntimeException {



    public VehicleMakeNotFoundException(Integer id){
        super("Could Not Find Vehicle Make: " + id);
    }

    public VehicleMakeNotFoundException(String vehicleMake){
        super("Could not find Vehicle Make: " + vehicleMake);
    }

}
