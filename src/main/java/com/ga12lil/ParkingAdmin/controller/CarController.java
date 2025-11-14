package com.ga12lil.ParkingAdmin.controller;

import com.ga12lil.ParkingAdmin.model.Car;
import com.ga12lil.ParkingAdmin.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarRepository repo;

    @GetMapping
    public List<Car> all() { return repo.findAll(); }

    @GetMapping("/search")
    public List<Car> search(@RequestParam("q") String q) { return repo.findByLicenseLike(q); }

    @GetMapping("/{id}")
    public Car get(@PathVariable int id) { return repo.findById(id); }

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody Car car) {
        int id = repo.create(car);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Car car) {
        car.setId(id);
        repo.update(car);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        repo.delete(id);
        return ResponseEntity.ok().build();
    }
}
