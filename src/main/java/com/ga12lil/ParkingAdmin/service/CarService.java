package com.ga12lil.ParkingAdmin.service;

import com.ga12lil.ParkingAdmin.model.Car;
import com.ga12lil.ParkingAdmin.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repo;

    public List<Car> getAll() {
        return repo.findAll();
    }

    public List<Car> search(String license) {
        return repo.findByLicenseLike(license);
    }

    public Car getById(int id) {
        return repo.findById(id);
    }

    public ResponseEntity<Integer> create(Car car) {
        int id = repo.create(car);
        return ResponseEntity.ok(id);
    }

    public ResponseEntity<Void> update(int id, Car car) {
        car.setId(id);
        repo.update(car);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> delete(int id) {
        repo.delete(id);
        return ResponseEntity.ok().build();
    }
}
