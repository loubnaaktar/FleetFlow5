package org.example.fleetflow.mapper;

import org.example.fleetflow.DTO.ChauffeurDTO;
import org.example.fleetflow.model.Chauffeur;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChauffeurMapper {

    Chauffeur toEntity(ChauffeurDTO dto);

    ChauffeurDTO toDTO(Chauffeur chauffeur);

    void modifierChauffeurDto(ChauffeurDTO dto, @MappingTarget Chauffeur entity);

    List<ChauffeurDTO> toDTO(List<Chauffeur> chauffeurs);
}
