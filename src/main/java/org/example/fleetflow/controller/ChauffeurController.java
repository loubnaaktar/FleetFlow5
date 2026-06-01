package org.example.fleetflow.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.fleetflow.DTO.ChauffeurDTO;
import org.example.fleetflow.service.implementations.ChauffeurServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chauffeures")

@AllArgsConstructor
public class ChauffeurController {
    private final ChauffeurServiceImpl chauffeurService;
    @GetMapping
    public List<ChauffeurDTO> listChauffeuresDispo(){
        return chauffeurService.cheuffeursDisponibles();
    }

    @PostMapping("/ajouter")
        public ChauffeurDTO ajouterChauffeur(@Valid @RequestBody ChauffeurDTO chauffeurDTO){
        return chauffeurService.ajouterChauffeur(chauffeurDTO);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimerChauffeur(@PathVariable long id){
        chauffeurService.supprimerChauffeur(id);
    }

  @PutMapping("/modifier/{id}")
    public ChauffeurDTO modifierChauffeur(@Valid @PathVariable long id, @RequestBody ChauffeurDTO chauffeurDTO){
       return chauffeurService.modifierChauffeur(id,chauffeurDTO);
  }




  //test
}
