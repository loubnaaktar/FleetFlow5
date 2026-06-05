package org.example.fleetflow.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fleetflow.DTO.LivraisonDTO;
import org.example.fleetflow.model.Livraison;
import org.example.fleetflow.service.implementations.LivraisonServiceImpl;
import org.example.fleetflow.model.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livraison")
@RequiredArgsConstructor
public class LivraisonController {

    private final LivraisonServiceImpl livraisonService;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping
    public LivraisonDTO create(@Valid @RequestParam long idClient,@RequestBody LivraisonDTO dto) {
        return livraisonService.ajouterLivraison(idClient,dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PutMapping("/{id}/assigner")
    public LivraisonDTO assigner(@Valid
            @PathVariable long id,
            @RequestParam long idChauffeur,
            @RequestParam long idVehicule){

        return livraisonService.assignerChauffeurVehicule(id,idChauffeur,idVehicule);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','CHAUFFEUR')")
    @GetMapping
    public Page<LivraisonDTO> getAll(@PageableDefault Pageable pageable, Authentication authentication){
        Utilisateur user = (Utilisateur) authentication.getPrincipal();
        
        if (user.getRole() == Utilisateur.Role.CHAUFFEUR) {
            return livraisonService.getLivraisonsParChauffeur(user.getId(), pageable);
        }
        
        return livraisonService.listLivraisons(pageable);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PutMapping("/{id}/statut")
    public LivraisonDTO updateStatut(@Valid
            @PathVariable long id,
            @RequestParam Livraison.StatutLivraison statut){
        return livraisonService.modifierStatutLivraison(id, statut);
    }
}





