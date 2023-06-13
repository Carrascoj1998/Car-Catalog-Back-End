package com.astontech.RestFA.domain;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class VehicleMake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleMakeId")
    private Integer id;


    private String vehicleMake;


    @OneToMany(cascade = CascadeType.ALL, fetch =FetchType.EAGER,orphanRemoval = true)
    private List<VehicleModel> vehicleModelList;

    public VehicleMake(){

    }

    public VehicleMake(String vehicleMake ){
        this.vehicleMake =vehicleMake;

    }

    public VehicleMake(String vehicleMake , List<VehicleModel> vehicleModelList){
        this.vehicleMake =vehicleMake;
        this.vehicleModelList = vehicleModelList;

    }

    public VehicleMake(List<VehicleModel> vehicleModelList){
        this.vehicleModelList = vehicleModelList;
    }

}
