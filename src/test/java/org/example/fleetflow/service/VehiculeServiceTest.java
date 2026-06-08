package org.example.fleetflow.service;

import org.example.fleetflow.DTO.VehiculeDTO;
import org.example.fleetflow.Repository.VehiculeRepository;
import org.example.fleetflow.mapper.VehiculeMapper;
import org.example.fleetflow.model.Vehicule;
import org.example.fleetflow.service.implementations.VehiculeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehiculeServiceTest {

    @Mock
    private VehiculeRepository vehiculeRepository;

    @Mock
    private VehiculeMapper vehiculeMapper;

    @InjectMocks
    private VehiculeServiceImpl vehiculeService;

    private Vehicule vehicule;
    private VehiculeDTO vehiculeDTO;

    @BeforeEach
    void setUp() {
        vehicule = new Vehicule();
        vehicule.setId(1L);
        vehicule.setCapacite(10);
        vehicule.setStatut(Vehicule.StatutVehicule.DISPONIBLE);

        vehiculeDTO = new VehiculeDTO();
    }

    @Test
    void getVehiculesDisponible(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Vehicule> page = new PageImpl<>(List.of(vehicule), pageable, 1);
        
        when(vehiculeRepository.findByStatut(Vehicule.StatutVehicule.DISPONIBLE, pageable))
                .thenReturn(page);
        when(vehiculeMapper.toDto(vehicule)).thenReturn(vehiculeDTO);
        
        Page<VehiculeDTO> result = vehiculeService.getVehiculesDisponible(pageable);
        assertEquals(1, result.getTotalElements());
        verify(vehiculeRepository).findByStatut(Vehicule.StatutVehicule.DISPONIBLE, pageable);
    }


    @Test
    void returVehiculeAvecCapacite(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Vehicule> page = new PageImpl<>(List.of(vehicule), pageable, 1);
        
        when(vehiculeRepository.findByCapaciteGreaterThan(6, pageable))
                .thenReturn(page);
        when(vehiculeMapper.toDto(vehicule)).thenReturn(vehiculeDTO);
        
        Page<VehiculeDTO> res = vehiculeService.getVehiculeByCapacite(6, pageable);
        assertFalse(res.isEmpty());
        assertEquals(1, res.getTotalElements());
        verify(vehiculeRepository).findByCapaciteGreaterThan(6, pageable);

    }

}