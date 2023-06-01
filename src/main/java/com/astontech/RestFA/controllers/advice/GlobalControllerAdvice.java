package com.astontech.RestFA.controllers.advice;

import com.astontech.RestFA.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalControllerAdvice {


    @ExceptionHandler(VehicleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String vehicleNotFoundHandler(VehicleNotFoundException vEx){
        return vEx.getLocalizedMessage();
    }


    @ExceptionHandler(FieldNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String fieldNotFoundHandler(FieldNotFoundException fEx){
        return fEx.getLocalizedMessage();
    }

    @ExceptionHandler(VehicleModelNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String vehicleModelNotFoundHandler(VehicleModelNotFoundException vmEx){
        return vmEx.getLocalizedMessage();
    }

    @ExceptionHandler(VehicleMakeNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String vehicleModelNotFoundHandler(VehicleMakeNotFoundException vmEx){
        return vmEx.getLocalizedMessage();
    }



    @ExceptionHandler(VinAlreadyInDB.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String vehicleModelNotFoundHandler(VinAlreadyInDB vinEx){
        return vinEx.getLocalizedMessage();
    }


    @ExceptionHandler(VehicleModelAndCreatDateInDBAlready.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String vehicleModelNotFoundHandler(VehicleModelAndCreatDateInDBAlready vmcdEx){
        return vmcdEx.getLocalizedMessage();
    }

    @ExceptionHandler(VehicleMakeAlreadyInDB.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String vehicleModelNotFoundHandler(VehicleMakeAlreadyInDB vmdbEx){
        return vmdbEx.getLocalizedMessage();
    }

    //General Exception Catch
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String generalExceptionHandler(Exception ex){
        return "General Server Error";
    }





}
