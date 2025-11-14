package com.ga12lil.ParkingAdmin.model;

import lombok.Data;

@Data
public class Car {
    private Integer id;
    private Integer ownerId;
    private String licensePlate;
    private String make;
    private String model;
    private String color;
}
