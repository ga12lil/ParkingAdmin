package com.ga12lil.ParkingAdmin.controller;

import com.ga12lil.ParkingAdmin.model.Car;
import com.ga12lil.ParkingAdmin.repository.CarRepository;
import com.ga12lil.ParkingAdmin.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public List<Car> all() {
        return carService.getAll();
    }

    @GetMapping("/search")
    public List<Car> search(@RequestParam("q") String q) {
        return carService.search(q);
    }

    @GetMapping("/{id}")
    public Car get(@PathVariable int id) {
        return carService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody Car car) {
        return carService.create(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Car car) {
        return carService.update(id, car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return carService.delete(id);
    }
}
