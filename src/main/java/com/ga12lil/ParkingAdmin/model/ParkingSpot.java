package com.ga12lil.ParkingAdmin.model;

import lombok.Data;

@Data
public class ParkingSpot {
    private Integer id;
    private String spotNumber;
    private Integer level;
    private Boolean isAvailable;
}
