package org.example.fleetflow.mapper;
import org.example.fleetflow.DTO.ChauffeurDTO;
import org.example.fleetflow.DTO.LivraisonDTO;
import org.example.fleetflow.model.Chauffeur;
import org.example.fleetflow.model.Livraison;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LivraisonMapper {
   Livraison toEntity(LivraisonDTO livraisonDTO);
   LivraisonDTO toDTO(Livraison livraison);
   List<LivraisonDTO> toDTO(List<Livraison> livraisons);

}
