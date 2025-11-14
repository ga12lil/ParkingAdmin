package com.ga12lil.ParkingAdmin.controller;

import com.ga12lil.ParkingAdmin.model.Reservation;
import com.ga12lil.ParkingAdmin.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationRepository repo;

    @GetMapping
    public List<Reservation> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Reservation get(@PathVariable int id) { return repo.findById(id); }

    @GetMapping("/by-car")
    public List<Reservation> byCar(@RequestParam("license") String license) { return repo.findByCarLicense(license); }

    @GetMapping("/by-owner")
    public List<Reservation> byOwner(@RequestParam("q") String q) { return repo.findByOwnerName(q); }

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody Reservation r) {
        int id = repo.create(r);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Reservation r) {
        r.setId(id);
        repo.update(r);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<Void> pay(@PathVariable int id) {
        repo.markPaid(id, true);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable int id) {
        repo.cancel(id);
        return ResponseEntity.ok().build();
    }
}
