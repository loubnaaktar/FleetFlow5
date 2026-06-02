package org.example.fleetflow.service.interfaces;

import org.example.fleetflow.DTO.LivraisonDTO;
import org.example.fleetflow.model.Livraison;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LivraisonService {
    LivraisonDTO ajouterLivraison(long idClient, LivraisonDTO dto);
    LivraisonDTO assignerChauffeurVehicule(long idLivraison, long idChauffeur, long idVehicule);
    Page<LivraisonDTO> listLivraisons(Pageable pageable);
    LivraisonDTO modifierStatutLivraison(long idLivraison, Livraison.StatutLivraison statut);

}
