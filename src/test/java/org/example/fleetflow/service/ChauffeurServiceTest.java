package org.example.fleetflow.service;

import org.example.fleetflow.DTO.ChauffeurDTO;
import org.example.fleetflow.Repository.ChauffeurRepository;
import org.example.fleetflow.mapper.ChauffeurMapper;
import org.example.fleetflow.model.Chauffeur;
import org.example.fleetflow.service.implementations.ChauffeurServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ChauffeurServiceTest {

    @Mock
    ChauffeurRepository chauffeurRepository;
    @Mock
    ChauffeurMapper chauffeurMapper;
    @InjectMocks
    ChauffeurServiceImpl chauffeurService;

    @Test
    void cheuffeursDisponibles() {
        Chauffeur chauffeur1 = new Chauffeur();
        List<Chauffeur> chauffeurs = List.of(chauffeur1);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Chauffeur> chauffeurPage = new PageImpl<>(chauffeurs, pageable, 1);

        Mockito.when(chauffeurRepository.findByDisponible(true, pageable)).thenReturn(chauffeurPage);
        Mockito.when(chauffeurMapper.toDTO(chauffeur1)).thenReturn(new ChauffeurDTO());

        Page<ChauffeurDTO> result = chauffeurService.cheuffeursDisponibles(pageable);
        assertEquals(1, result.getTotalElements());
    }

}