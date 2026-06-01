package org.example.fleetflow.service.interfaces;

import org.example.fleetflow.DTO.VehiculeDTO;

import java.util.List;

public interface VehiculeService {
    VehiculeDTO createVehicule(VehiculeDTO dto);
    List<VehiculeDTO> getVehiculesDisponible();
    List<VehiculeDTO> getAll();
    VehiculeDTO getById(Long id);
    VehiculeDTO update(Long id, VehiculeDTO dto);
    void delete(Long id);
    List<VehiculeDTO> getVehiculeByCapacite(Integer capacite);
}
