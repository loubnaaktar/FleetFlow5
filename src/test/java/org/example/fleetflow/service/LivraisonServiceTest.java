package org.example.fleetflow.service;

import org.example.fleetflow.DTO.LivraisonDTO;
import org.example.fleetflow.Repository.ChauffeurRepository;
import org.example.fleetflow.Repository.ClientRepository;
import org.example.fleetflow.Repository.LivraisonRepository;
import org.example.fleetflow.Repository.VehiculeRepository;
import org.example.fleetflow.mapper.LivraisonMapper;
import org.example.fleetflow.model.Chauffeur;
import org.example.fleetflow.model.Client;
import org.example.fleetflow.model.Livraison;
import org.example.fleetflow.model.Vehicule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LivraisonServiceTest {

    @Mock
    LivraisonRepository livraisonRepository;
    @Mock
    LivraisonMapper livraisonMapper;
    @Mock
    ClientRepository clientRepository;
    @Mock
    ChauffeurRepository chauffeurRepository;
    @Mock
    VehiculeRepository vehiculeRepository;
    @InjectMocks
    LivraisonService livraisonService;


    @Test
    public void ajouterLivraison() {
        Client client = new Client();
        client.setId(1L);
        client.setNom("ahmed");

        LivraisonDTO inputDTO = new LivraisonDTO();
        inputDTO.setAdresseDepart("casa");
        inputDTO.setAdresseDestination("rabat");

        Livraison savedLivraison = new Livraison();
        savedLivraison.setId(1L);
        savedLivraison.setAdresseDepart("casa");
        savedLivraison.setAdresseDestination("rabat");
        savedLivraison.setClient(client);
        savedLivraison.setStatut(Livraison.StatutLivraison.ENATTENTE);

        LivraisonDTO expectedDTO = new LivraisonDTO();
        expectedDTO.setId(1L);
        expectedDTO.setAdresseDepart("casa");
        expectedDTO.setAdresseDestination("rabat");
        expectedDTO.setStatut(Livraison.StatutLivraison.ENATTENTE);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(livraisonRepository.save(any(Livraison.class))).thenReturn(savedLivraison);
        when(livraisonMapper.toDTO(any(Livraison.class))).thenReturn(expectedDTO);

        LivraisonDTO livraisonResponse = livraisonService.ajouterLivraison(1L, inputDTO);

        assertNotNull(livraisonResponse);
        assertEquals(expectedDTO.getStatut(), livraisonResponse.getStatut());
        assertEquals(expectedDTO.getAdresseDepart(), livraisonResponse.getAdresseDepart());
    }

    @Test
    void assignerChauffeurVehicule() {
        Livraison livraison = new Livraison();
        livraison.setId(1L);

        Chauffeur chauffeur = new Chauffeur();
        chauffeur.setId(2L);
        chauffeur.setNom("ahmed");
        chauffeur.setDisponible(true);

        Vehicule vehicule = new Vehicule();
        vehicule.setId(3L);
        vehicule.setMatricule("AB28");
        vehicule.setStatut(Vehicule.StatutVehicule.DISPONIBLE);

        LivraisonDTO livraisonDTO = new LivraisonDTO();
        livraisonDTO.setId(1L);
        livraisonDTO.setChauffeur(chauffeur);
        livraisonDTO.setVehicule(vehicule);

        when(chauffeurRepository.findById(2L)).thenReturn(Optional.of(chauffeur));
        when(vehiculeRepository.findById(3L)).thenReturn(Optional.of(vehicule));
        when(livraisonRepository.findById(1L)).thenReturn(Optional.of(livraison));
        when(livraisonRepository.save(livraison)).thenReturn(livraison);
        when(livraisonMapper.toDTO(livraison)).thenReturn(livraisonDTO);

        LivraisonDTO resultat = livraisonService.assignerChauffeurVehicule(1L,2L,3L);

        assertEquals("ahmed",resultat.getChauffeur().getNom());

    }

    @Test
    void modifierStatutLivraison() {


        Livraison livraison = new Livraison();
        livraison.setId(1L);
        livraison.setStatut(Livraison.StatutLivraison.ENATTENTE);

        Livraison mod = new Livraison();
        mod.setId(1L);
        mod.setStatut(Livraison.StatutLivraison.LIVREE);

        LivraisonDTO livraisonDTO = new LivraisonDTO();
        livraisonDTO.setStatut(Livraison.StatutLivraison.LIVREE);


        when(livraisonRepository.findById(1L)).thenReturn(Optional.of(livraison));


        when(livraisonRepository.save(Mockito.any(Livraison.class))).thenReturn(mod);


        when(livraisonMapper.toDTO(mod)).thenReturn(livraisonDTO);

        LivraisonDTO dto = livraisonService.modifierStatutLivraison(1L, Livraison.StatutLivraison.LIVREE);

        assertEquals(Livraison.StatutLivraison.LIVREE, dto.getStatut());
    }
}
