package org.example.fleetflow.service.implementations;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow.DTO.VehiculeDTO;
import org.example.fleetflow.Repository.VehiculeRepository;
import org.example.fleetflow.mapper.VehiculeMapper;
import org.example.fleetflow.model.Vehicule;
import org.example.fleetflow.service.interfaces.VehiculeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements VehiculeService {
    private final VehiculeRepository vehiculeRepository;
    private final VehiculeMapper vehiculeMapper;

    @Override
    public VehiculeDTO createVehicule(VehiculeDTO dto) {
        Vehicule vehicule = vehiculeMapper.toEntity(dto);
        Vehicule saved = vehiculeRepository.save(vehicule);
        return vehiculeMapper.toDto(saved);
    }

    @Override
    public Page<VehiculeDTO> getVehiculesDisponible(Pageable pageable) {
       Page<Vehicule> vehiculePage =  vehiculeRepository.findByStatut(Vehicule.StatutVehicule.DISPONIBLE,pageable);
       return vehiculePage.map(vehiculeMapper::toDto);

    }

    @Override
    public Page<VehiculeDTO> getAll(Pageable pageable) {
       Page<Vehicule> vehiculePage =  vehiculeRepository.findAll(pageable);
              return vehiculePage.map(vehiculeMapper::toDto);
    }

    public VehiculeDTO getById(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("vehicule introuvable"));
        return vehiculeMapper.toDto(vehicule);
    }

    @Override

    public VehiculeDTO update(Long id, VehiculeDTO dto) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("vehicule introuvable"));
        vehiculeMapper.updateEntityFromDto(dto, vehicule);
        return vehiculeMapper.toDto(vehiculeRepository.save(vehicule));
    }

    @Override
    public void delete(Long id) {
        vehiculeRepository.deleteById(id);
    }

    @Override
    public Page<VehiculeDTO> getVehiculeByCapacite(Integer capacite,Pageable pageable){
      Page<Vehicule> vehiculePage =  vehiculeRepository.findByCapaciteGreaterThan(capacite,pageable);
      return vehiculePage.map(vehiculeMapper::toDto);
    }

}
