package com.ga12lil.ParkingAdmin.service;

import com.ga12lil.ParkingAdmin.model.Owner;
import com.ga12lil.ParkingAdmin.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository repo;

    public List<Owner> getAll() {
        return repo.findAll();
    }

    public List<Owner> search(String name) {
        return repo.findByName(name);
    }

    public Owner getById(int id) {
        return repo.findById(id);
    }

    public ResponseEntity<Integer> create(Owner owner) {
        int id = repo.create(owner);
        return ResponseEntity.ok(id);
    }

    public ResponseEntity<Void> update(int id, Owner owner) {
        owner.setId(id);
        repo.update(owner);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> delete(int id) {
        repo.delete(id);
        return ResponseEntity.ok().build();
    }
}
