package com.ga12lil.ParkingAdmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Integer id;
    private Integer spotId;
    private Integer carId;
    private Integer ownerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean paid;
}
