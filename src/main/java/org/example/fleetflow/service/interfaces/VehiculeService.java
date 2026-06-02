package org.example.fleetflow.service.interfaces;

import org.example.fleetflow.DTO.VehiculeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehiculeService {
    VehiculeDTO createVehicule(VehiculeDTO dto);
    Page<VehiculeDTO> getVehiculesDisponible(Pageable pageable);
    Page<VehiculeDTO> getAll(Pageable pageable);
    VehiculeDTO getById(Long id);
    VehiculeDTO update(Long id, VehiculeDTO dto);
    void delete(Long id);
    Page<VehiculeDTO> getVehiculeByCapacite(Integer capacite, Pageable pageable);

}
