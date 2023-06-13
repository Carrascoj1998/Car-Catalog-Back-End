package com.astontech.RestFA.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleModelId")
    private Integer id;

    private String vehicleModel;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Vehicle> vehicleList ;

    public  VehicleModel(){

    }


    public VehicleModel(String vehicleModel){
        this.vehicleModel =vehicleModel;

    }


    public VehicleModel(String vehicleModel, List<Vehicle> vehicleList) {
        this.vehicleModel = vehicleModel;
        this.vehicleList=vehicleList;


    }

    public VehicleModel(List<Vehicle> vehicleList){
        this.vehicleList = vehicleList;
    }


}
