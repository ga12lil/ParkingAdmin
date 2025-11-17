package com.ga12lil.ParkingAdmin.controller;

import com.ga12lil.ParkingAdmin.model.ParkingSpot;
import com.ga12lil.ParkingAdmin.repository.SpotRepository;
import com.ga12lil.ParkingAdmin.service.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spots")
@RequiredArgsConstructor
public class SpotController {
    private final SpotService spotService;

    @GetMapping
    public List<ParkingSpot> all() {
        return spotService.getAll();
    }

    @GetMapping("/{id}")
    public ParkingSpot get(@PathVariable int id) {
        return spotService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody ParkingSpot s) {
        return spotService.create(s);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody ParkingSpot s) {
        return spotService.update(id, s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return spotService.delete(id);
    }
}
