package org.example.fleetflow.service.interfaces;

import org.example.fleetflow.DTO.ChauffeurDTO;
import org.example.fleetflow.model.Chauffeur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChauffeurService {
    ChauffeurDTO ajouterChauffeur(ChauffeurDTO chauffeurDTO);
    ChauffeurDTO modifierChauffeur(long idChauffeur , ChauffeurDTO chauffeurDTO);
    Chauffeur chercherChauffeurParId(long idChauffeur);
    Page<ChauffeurDTO> cheuffeursDisponibles(Pageable pageable);
    void supprimerChauffeur(long id);
}
