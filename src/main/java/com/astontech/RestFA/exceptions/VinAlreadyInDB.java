package com.astontech.RestFA.exceptions;

public class VinAlreadyInDB extends RuntimeException {


    public VinAlreadyInDB(String vin){
        super("Vin is already in the database :" + vin);
    }

}
