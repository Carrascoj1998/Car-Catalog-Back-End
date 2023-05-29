package com.astontech.RestFA.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleId")
    private Integer id;

    private String year;
    private String color;
    private String purchasePrice;

    public Vehicle(){

    }

    public Vehicle(String year, String color, String purchasePrice){
        this.year =year;
        this.color =color;
        this.purchasePrice = purchasePrice;
    }




}
