package org.example.fleetflow.service;

import org.example.fleetflow.DTO.VehiculeDTO;
import org.example.fleetflow.Repository.VehiculeRepository;
import org.example.fleetflow.mapper.VehiculeMapper;
import org.example.fleetflow.model.Vehicule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private VehiculeService vehiculeService;

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
        when(vehiculeRepository.findByStatut(Vehicule.StatutVehicule.DISPONIBLE))
                .thenReturn(List.of(vehicule));
        when(vehiculeMapper.toDto(vehicule)).thenReturn(vehiculeDTO);
        List<VehiculeDTO> result = vehiculeService.getVehiculesDisponible();
        assertEquals(1,result.size());
        verify(vehiculeRepository).findByStatut(Vehicule.StatutVehicule.DISPONIBLE);
    }


    @Test
    void returVehiculeAvecCapacite(){
        when(vehiculeRepository.findByCapaciteGreaterThan(6))
                .thenReturn(List.of(vehicule));
        when(vehiculeMapper.toDto(vehicule)).thenReturn(vehiculeDTO);
        List<VehiculeDTO> res =vehiculeService.getVehiculeByCapacite(6);
        assertFalse(res.isEmpty());
        assertEquals(1,res.size());
        verify(vehiculeRepository).findByCapaciteGreaterThan(6);

    }

}