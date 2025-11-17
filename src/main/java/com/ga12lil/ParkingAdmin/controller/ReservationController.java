package com.ga12lil.ParkingAdmin.controller;

import com.ga12lil.ParkingAdmin.model.Reservation;
import com.ga12lil.ParkingAdmin.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public List<Reservation> all() { return reservationService.getAll(); }

    @GetMapping("/{id}")
    public Reservation get(@PathVariable int id) { return reservationService.getById(id); }

    @GetMapping("/by-car")
    public List<Reservation> byCar(@RequestParam("license") String license) {
        return reservationService.getByCarLicense(license);
    }

    @GetMapping("/by-owner")
    public List<Reservation> byOwner(@RequestParam("q") String q) { return reservationService.getByOwnerName(q); }

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody Reservation r) {
        return reservationService.create(r);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Reservation r) {
        return reservationService.update(id, r);
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<Void> pay(@PathVariable int id) {
        return reservationService.pay(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable int id) {
        return reservationService.cancel(id);
    }
}
