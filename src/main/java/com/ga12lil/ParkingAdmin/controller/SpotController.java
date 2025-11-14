package com.ga12lil.ParkingAdmin.controller;

import com.ga12lil.ParkingAdmin.model.ParkingSpot;
import com.ga12lil.ParkingAdmin.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spots")
@RequiredArgsConstructor
public class SpotController {
    private final SpotRepository repo;

    @GetMapping
    public List<ParkingSpot> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ParkingSpot get(@PathVariable int id) { return repo.findById(id); }

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody ParkingSpot s) {
        int id = repo.create(s);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody ParkingSpot s) {
        s.setId(id);
        repo.update(s);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        repo.delete(id);
        return ResponseEntity.ok().build();
    }
}
