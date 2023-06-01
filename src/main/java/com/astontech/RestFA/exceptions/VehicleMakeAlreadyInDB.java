package com.astontech.RestFA.exceptions;

public class VehicleMakeAlreadyInDB extends RuntimeException {

    public VehicleMakeAlreadyInDB(String vehicleMake){
        super("Vehicle Make: " + "'" + vehicleMake + "'" + "is already in the Data base");
    }


}
