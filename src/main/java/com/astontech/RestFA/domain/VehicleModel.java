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

    private String createDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vehicle> vehicleList ;

    public  VehicleModel(){

    }


    public VehicleModel(String vehicleModel, String createDate){
        this.vehicleModel =vehicleModel;
        this.createDate=createDate;
    }


    public VehicleModel(String vehicleModel, List<Vehicle> vehicleList, String createDate){
        this.vehicleModel = vehicleModel;
        this.vehicleList=vehicleList;
        this.createDate = createDate;

    }

    public VehicleModel(List<Vehicle> vehicleList){
        this.vehicleList = vehicleList;
    }


}
