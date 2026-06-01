package org.example.fleetflow.service.implementations;

import lombok.AllArgsConstructor;
import org.example.fleetflow.DTO.ChauffeurDTO;
import org.example.fleetflow.Repository.ChauffeurRepository;
import org.example.fleetflow.mapper.ChauffeurMapper;
import org.example.fleetflow.model.Chauffeur;
import org.example.fleetflow.service.interfaces.ChauffeurService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor


public class ChauffeurServiceImpl implements ChauffeurService {

private final ChauffeurRepository chauffeurRepository;
private final ChauffeurMapper mapper;

@Override
public ChauffeurDTO ajouterChauffeur(ChauffeurDTO chauffeurDTO){
    Chauffeur chauffeur = mapper.toEntity(chauffeurDTO);
   Chauffeur saved = chauffeurRepository.save(chauffeur);
   return mapper.toDTO(saved);
}

@Override
public ChauffeurDTO modifierChauffeur(long idChauffeur , ChauffeurDTO chauffeurDTO){
Chauffeur chauffeur = chercherChauffeurParId(idChauffeur);
mapper.modifierChauffeurDto(chauffeurDTO, chauffeur);
    Chauffeur saved = chauffeurRepository.save(chauffeur);
    return mapper.toDTO(saved);
}

@Override
public Chauffeur chercherChauffeurParId(long idChauffeur){
  return chauffeurRepository.findById(idChauffeur).orElseThrow(() -> new RuntimeException("Chauffeur not found"));
}

@Override
public List<ChauffeurDTO> cheuffeursDisponibles(){
    List<Chauffeur> listDsipo = chauffeurRepository.findByDisponible(true);
    return mapper.toDTO(listDsipo);
}

@Override
public void supprimerChauffeur(long id){
    Chauffeur chauffeur = chercherChauffeurParId(id);
    chauffeurRepository.delete(chauffeur);
}

//public List<ChauffeurDTO> allChauffeurs(){
//    List<Chauffeur> chauffeurs = chauffeurRepository.findAll();
//    return mapper.toDTO(chauffeurs);
//}
}
