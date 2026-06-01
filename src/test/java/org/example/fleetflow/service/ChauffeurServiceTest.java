package org.example.fleetflow.service;

import org.example.fleetflow.DTO.ChauffeurDTO;
import org.example.fleetflow.Repository.ChauffeurRepository;
import org.example.fleetflow.mapper.ChauffeurMapper;
import org.example.fleetflow.model.Chauffeur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChauffeurServiceTest {

@Mock
    ChauffeurRepository chauffeurRepository;
@Mock
    ChauffeurMapper chauffeurMapper;
@InjectMocks
ChauffeurService chauffeurService;
@BeforeEach
void initialisation(){
    Chauffeur chauffeur1 = new Chauffeur();
    Chauffeur chauffeur2 = new Chauffeur();
    Chauffeur chauffeur3 = new Chauffeur();
    Chauffeur chauffeur4 = new Chauffeur();
    List<Chauffeur> chauffeurs = List.of(chauffeur1,chauffeur2,chauffeur3,chauffeur4);
List<ChauffeurDTO> chauffeurDTOS = List.of(
   new ChauffeurDTO(),
   new ChauffeurDTO(),
   new ChauffeurDTO(),
   new ChauffeurDTO()
        );
Mockito.when(chauffeurRepository.findByDisponible(true)).thenReturn(chauffeurs);
    Mockito.when(chauffeurMapper.toDTO(chauffeurs)).thenReturn(chauffeurDTOS);
}
    @Test
    void cheuffeursDisponibles() {
       List<ChauffeurDTO> chauffeurDTOList = chauffeurService.cheuffeursDisponibles();
       assertEquals(4,chauffeurDTOList.size());
    }

}