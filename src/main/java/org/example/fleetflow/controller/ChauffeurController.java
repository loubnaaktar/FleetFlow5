package org.example.fleetflow.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.fleetflow.DTO.ChauffeurDTO;
import org.example.fleetflow.DTO.LivraisonDTO;
import org.example.fleetflow.model.Livraison;
import org.example.fleetflow.model.Utilisateur;
import org.example.fleetflow.service.implementations.ChauffeurServiceImpl;
import org.example.fleetflow.service.implementations.LivraisonServiceImpl;
import org.example.fleetflow.service.interfaces.LivraisonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chauffeures")

@AllArgsConstructor
public class ChauffeurController {
    private final ChauffeurServiceImpl chauffeurService;
    private final LivraisonServiceImpl service;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Page<ChauffeurDTO> listChauffeuresDispo(@PageableDefault(sort = "nom",
            direction = Sort.Direction.ASC) Pageable pageable) {
        return chauffeurService.cheuffeursDisponibles(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public ChauffeurDTO ajouterChauffeur(@Valid @RequestBody ChauffeurDTO chauffeurDTO) {
        return chauffeurService.ajouterChauffeur(chauffeurDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id}")
    public void supprimerChauffeur(@PathVariable long id) {
        chauffeurService.supprimerChauffeur(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/modifier/{id}")
    public ChauffeurDTO modifierChauffeur(@Valid @PathVariable long id, @RequestBody ChauffeurDTO chauffeurDTO) {
        return chauffeurService.modifierChauffeur(id, chauffeurDTO);
    }

//    @PreAuthorize("hasRole('CHAUFFEUR')")
//    @GetMapping("/my-livraisons")
//    public Page<LivraisonDTO> getMyLivraisons(Long id , Pageable pageable) {
//        return service.livraisonsChauffeur(id,pageable);
//    }

    @PreAuthorize("hasRole('CHAUFFEUR')")
  @GetMapping("/my-livraisons")
    public Page<LivraisonDTO> getLivraisonsChauffeur(Long id,Pageable pageable){
        return chauffeurService.livraisonsChauffeur(id,pageable);
    }
}
