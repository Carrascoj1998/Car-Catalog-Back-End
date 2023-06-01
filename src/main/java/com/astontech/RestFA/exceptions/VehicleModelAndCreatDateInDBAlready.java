package com.astontech.RestFA.exceptions;

public class VehicleModelAndCreatDateInDBAlready extends RuntimeException{


    public VehicleModelAndCreatDateInDBAlready(String vehicleModel, String createDate){
        super("Vehicle model: " + vehicleModel + " and CreateDate: " + createDate +
                " is already in the database!");
    }


}
