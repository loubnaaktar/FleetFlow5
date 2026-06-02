package org.example.fleetflow.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fleetflow.DTO.LivraisonDTO;
import org.example.fleetflow.model.Livraison;
import org.example.fleetflow.service.implementations.LivraisonServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livraison")
@RequiredArgsConstructor
public class LivraisonController {

    private final LivraisonServiceImpl livraisonService;

    @PostMapping
    public LivraisonDTO create(@Valid @RequestParam long idClient,@RequestBody LivraisonDTO dto) {
        return livraisonService.ajouterLivraison(idClient,dto);
    }

    @PutMapping("/{id}/assigner")
    public LivraisonDTO assigner(@Valid
            @PathVariable long id,
            @RequestParam long idChauffeur,
            @RequestParam long idVehicule){

        return livraisonService.assignerChauffeurVehicule(id,idChauffeur,idVehicule);
    }

    @GetMapping
    public Page<LivraisonDTO> getAll(@ Pageable pageable){
        return livraisonService.listLivraisons(pageable);
    }

    @PutMapping("/{id}/statut")
    public LivraisonDTO updateStatut(@Valid
            @PathVariable long id,
            @RequestParam Livraison.StatutLivraison statut){
        return livraisonService.modifierStatutLivraison(id, statut);
    }
}





