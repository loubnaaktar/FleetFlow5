package org.example.fleetflow.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fleetflow.DTO.VehiculeDTO;
import org.example.fleetflow.service.implementations.VehiculeServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final VehiculeServiceImpl service;

    @PostMapping
    public VehiculeDTO create(@Valid  @RequestBody VehiculeDTO dto) {
        return service.createVehicule(dto);
    }

    @GetMapping
    public Page<VehiculeDTO> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public VehiculeDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public VehiculeDTO update(@Valid @PathVariable Long id, @RequestBody VehiculeDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}