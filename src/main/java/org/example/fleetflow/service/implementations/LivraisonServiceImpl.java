package org.example.fleetflow.service.implementations;

import lombok.AllArgsConstructor;
import org.example.fleetflow.DTO.LivraisonDTO;
import org.example.fleetflow.Repository.*;
import org.example.fleetflow.mapper.LivraisonMapper;
import org.example.fleetflow.model.*;
import org.example.fleetflow.service.interfaces.LivraisonService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.example.fleetflow.model.Vehicule.StatutVehicule.DISPONIBLE;
import static org.example.fleetflow.model.Vehicule.StatutVehicule.EN_LIVRAISON;

@Service
@AllArgsConstructor
public class LivraisonServiceImpl implements LivraisonService {
    private final LivraisonRepository livraisonRepository;
    private final ClientRepository clientRepository;
    private final LivraisonMapper livraisonMapper;
    private final ChauffeurRepository chauffeurRepository;
    private final VehiculeRepository vehiculeRepository;
    private final UtilisateurRepository userrepo;

    @Override
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

@Override
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

@Override
public Page<LivraisonDTO> listLivraisons(Pageable pageable){
Page<Livraison> pageLivraisons = livraisonRepository.findAll(pageable);
return pageLivraisons.map(livraisonMapper::toDTO);
}

@Override
public LivraisonDTO modifierStatutLivraison(long idLivraison, Livraison.StatutLivraison statut){
    Livraison livraison = livraisonRepository.findById(idLivraison).orElseThrow(() ->new RuntimeException("Livraison introuvable"));
    livraison.setStatut(statut);
    Livraison liv = livraisonRepository.save(livraison);
    return livraisonMapper.toDTO(liv);
}

//public Page<LivraisonDTO> livraisonsChauffeur(Long id,Pageable pageable){
//
//    Utilisateur user = userrepo.findById(id).orElseThrow(()->new RuntimeException("ve ne puvez pas consultez"));
//    if(user.getRole() == Utilisateur.Role.CHAUFFEUR){
//        Page<Livraison> livraisonsPage = livraisonRepository.findLivraisonsByChauffeur_Id(id,pageable);
//        return livraisonsPage.map(livraisonMapper::toDTO);
//    }
//return null;
//}
}
