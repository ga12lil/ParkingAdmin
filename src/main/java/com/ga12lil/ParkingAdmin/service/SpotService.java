package com.ga12lil.ParkingAdmin.service;

import com.ga12lil.ParkingAdmin.model.ParkingSpot;
import com.ga12lil.ParkingAdmin.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotService {
    private final SpotRepository repo;

    public List<ParkingSpot> getAll() {
        return repo.findAll();
    }

    public ParkingSpot getById(int id) {
        return repo.findById(id);
    }

    public ResponseEntity<Integer> create(ParkingSpot spot) {
        int id = repo.create(spot);
        return ResponseEntity.ok(id);
    }

    public ResponseEntity<Void> update(int id, ParkingSpot spot) {
        spot.setId(id);
        repo.update(spot);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> delete(int id) {
        repo.delete(id);
        return ResponseEntity.ok().build();
    }
}
