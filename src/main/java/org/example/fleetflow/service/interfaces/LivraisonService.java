package org.example.fleetflow.service.interfaces;

import org.example.fleetflow.DTO.LivraisonDTO;
import org.example.fleetflow.model.Livraison;

import java.util.List;

public interface LivraisonService {
    LivraisonDTO ajouterLivraison(long idClient, LivraisonDTO dto);
    LivraisonDTO assignerChauffeurVehicule(long idLivraison, long idChauffeur, long idVehicule);
    List<LivraisonDTO> listLivraisons();
    LivraisonDTO modifierStatutLivraison(long idLivraison, Livraison.StatutLivraison statut);

}
