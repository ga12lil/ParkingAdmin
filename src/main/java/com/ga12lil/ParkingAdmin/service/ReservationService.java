package com.ga12lil.ParkingAdmin.service;

import com.ga12lil.ParkingAdmin.model.Car;
import com.ga12lil.ParkingAdmin.model.Reservation;
import com.ga12lil.ParkingAdmin.repository.CarRepository;
import com.ga12lil.ParkingAdmin.repository.ReservationRepository;
import com.ga12lil.ParkingAdmin.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final SpotRepository spotRepository;
    private final CarRepository carRepository;

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public Reservation getById(int id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> getByCarLicense(String license) {
        return reservationRepository.findByCarLicense(license);
    }

    public List<Reservation> getByOwnerName(String name) {
        return reservationRepository.findByOwnerName(name);
    }

    public ResponseEntity<Integer> create(Reservation r) {
        if (!spotRepository.isSpotFree(r.getSpotId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Место уже занято"
            );
        }
        Car car = carRepository.findById(r.getCarId());
        if (car == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Автомобиль не найден");

        int ownerId = car.getOwnerId();
        Reservation newReservation = new Reservation(
                r.getId(),
                r.getSpotId(),
                car.getId(),
                ownerId,
                r.getStartTime(),
                r.getEndTime(),
                r.getPaid()
        );
        int id = reservationRepository.create(newReservation);
        return ResponseEntity.ok(id);
    }

    public ResponseEntity<Void> update(int id, Reservation r) {
        r.setId(id);
        reservationRepository.update(r);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> pay(int id) {
        reservationRepository.markPaid(id, true);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> cancel(int id) {
        reservationRepository.cancel(id);
        return ResponseEntity.ok().build();
    }
}
