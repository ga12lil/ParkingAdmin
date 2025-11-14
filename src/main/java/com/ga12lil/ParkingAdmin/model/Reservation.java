package com.ga12lil.ParkingAdmin.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reservation {
    private Integer id;
    private Integer spotId;
    private Integer carId;
    private Integer ownerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean paid;
}
