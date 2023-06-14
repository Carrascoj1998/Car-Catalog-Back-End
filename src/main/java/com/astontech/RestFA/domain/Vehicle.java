package com.astontech.RestFA.domain;

import lombok.Data;
import org.springframework.ui.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleId")
    private Integer id;

    private String year;
    private String color;


    @Column(unique = true)
    private String vin;
    private String purchasePrice;

//    @ManyToOne
//    @JoinColumn(name = "vehicleMakeId") // Assuming the column name in the database for the make relationship is "make_id"
//    private String vehicleMake;
//
//    @ManyToOne
//    @JoinColumn(name = "vehicleModelId") // Assuming the column name in the database for the model relationship is "model_id"
//    private String vehicleModel;
//


    public Vehicle(){

    }

    public Vehicle(String year, String color, String purchasePrice, String vin){
        this.year =year;
        this.color =color;
        this.vin = vin;
        this.purchasePrice = purchasePrice;
    }




}
