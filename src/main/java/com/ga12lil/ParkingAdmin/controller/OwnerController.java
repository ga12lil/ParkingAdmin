package com.ga12lil.ParkingAdmin.controller;

import com.ga12lil.ParkingAdmin.model.Owner;
import com.ga12lil.ParkingAdmin.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerRepository repo;

    @GetMapping
    public List<Owner> all() { return repo.findAll(); }

    @GetMapping("/search")
    public List<Owner> search(@RequestParam("q") String q) { return repo.findByName(q); }

    @GetMapping("/{id}")
    public Owner get(@PathVariable int id) { return repo.findById(id); }


    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody Owner owner) {
        int id = repo.create(owner);
        return ResponseEntity.ok(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Owner owner) {
        owner.setId(id);
        repo.update(owner);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        repo.delete(id);
        return ResponseEntity.ok().build();
    }
}
