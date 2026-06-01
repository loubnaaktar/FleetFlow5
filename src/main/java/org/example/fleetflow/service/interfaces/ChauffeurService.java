package org.example.fleetflow.service.interfaces;

import org.example.fleetflow.DTO.ChauffeurDTO;
import org.example.fleetflow.model.Chauffeur;

import java.util.List;

public interface ChauffeurService {
    ChauffeurDTO ajouterChauffeur(ChauffeurDTO chauffeurDTO);
    ChauffeurDTO modifierChauffeur(long idChauffeur , ChauffeurDTO chauffeurDTO);
    Chauffeur chercherChauffeurParId(long idChauffeur);
    List<ChauffeurDTO> cheuffeursDisponibles();
    void supprimerChauffeur(long id);
}
