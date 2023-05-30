package com.astontech.RestFA.exceptions;

import com.astontech.RestFA.domain.Vehicle;

public class VehicleNotFoundException extends RuntimeException{

    public VehicleNotFoundException(Integer id){
        super("Could Not Find Vehicle: " + id);
    }

    public VehicleNotFoundException(String purchasePrice){
        super("Could not find Vehicle: " + purchasePrice);
    }



}
