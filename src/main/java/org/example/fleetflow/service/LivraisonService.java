package org.example.fleetflow.service;

import lombok.AllArgsConstructor;
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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.example.fleetflow.model.Vehicule.StatutVehicule.DISPONIBLE;
import static org.example.fleetflow.model.Vehicule.StatutVehicule.EN_LIVRAISON;

@Service
@AllArgsConstructor
public class LivraisonService {
    private final LivraisonRepository livraisonRepository;
    private final ClientRepository clientRepository;
    private final LivraisonMapper livraisonMapper;
    private final ChauffeurRepository chauffeurRepository;
    private final VehiculeRepository vehiculeRepository;

public LivraisonDTO ajouterLivraison(long idClient,LivraisonDTO dto){
Client client = clientRepository.findById(idClient).orElseThrow(() -> new RuntimeException("Client not found"));
Livraison livraison = new Livraison();
livraison.setClient(client);
livraison.setAdresseDepart(dto.getAdresseDepart());
livraison.setAdresseDestination(dto.getAdresseDestination());
livraison.setDateLivraison(LocalDateTime.now());
livraison.setStatut(Livraison.StatutLivraison.ENATTENTE);
return livraisonMapper.toDTO(livraisonRepository.save(livraison));
}


public LivraisonDTO assignerChauffeurVehicule(long idLivraison, long idChauffeur, long idVehicule){
Livraison livraison = livraisonRepository.findById(idLivraison).orElseThrow(() ->new RuntimeException("Livraison introuvable"));
Chauffeur chauffeur = chauffeurRepository.findById(idChauffeur).orElseThrow(()-> new RuntimeException("chauffeur introuvable"));
Vehicule vehicule = vehiculeRepository.findById(idVehicule).orElseThrow(() -> new RuntimeException("vehicule introuvable"));

if(!chauffeur.getDisponible()){
    throw new RuntimeException("aucun chauffeur est dispo");
}
if(!vehicule.getStatut().equals(DISPONIBLE)){
    throw new RuntimeException("aucune vehicule est dispo");
}
livraison.setChauffeur(chauffeur);
livraison.setVehicule(vehicule);

chauffeur.setDisponible(false);
vehicule.setStatut(EN_LIVRAISON);

Livraison liv = livraisonRepository.save(livraison);
return livraisonMapper.toDTO(liv);
}

public List<LivraisonDTO> listLivraisons(){
List<Livraison> listLivraisons = livraisonRepository.findAll();
return livraisonMapper.toDTO(listLivraisons);
}

public LivraisonDTO modifierStatutLivraison(long idLivraison, Livraison.StatutLivraison statut){
    Livraison livraison = livraisonRepository.findById(idLivraison).orElseThrow(() ->new RuntimeException("Livraison introuvable"));
    livraison.setStatut(statut);
    Livraison liv = livraisonRepository.save(livraison);
    return livraisonMapper.toDTO(liv);
}

}
