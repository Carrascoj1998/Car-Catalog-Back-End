package com.astontech.RestFA.domain;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.util.List;

@RedisHash("vehicleMM")
@Data
public class VehicleMakeAndModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleId")
    private Integer id;

    private String vehicleMake;
    private String vehicleModel;

    @OneToMany(cascade = CascadeType.ALL, fetch =FetchType.EAGER)
    private List<Vehicle> vehicleMakeAndModelList;


    public VehicleMakeAndModel(String vehicleMake, String vehicleModel){
        this.vehicleMake =vehicleMake;
        this.vehicleModel = vehicleModel;
    }

}
