package com.ga12lil.ParkingAdmin.controller;

import com.ga12lil.ParkingAdmin.model.Owner;
import com.ga12lil.ParkingAdmin.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping
    public List<Owner> all() {
        return ownerService.getAll();
    }

    @GetMapping("/search")
    public List<Owner> search(@RequestParam("q") String q) {
        return ownerService.search(q);
    }

    @GetMapping("/{id}")
    public Owner get(@PathVariable int id) {
        return ownerService.getById(id);
    }


    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody Owner owner) {
        return ownerService.create(owner);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Owner owner) {
        return ownerService.update(id, owner);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return ownerService.delete(id);
    }
}
