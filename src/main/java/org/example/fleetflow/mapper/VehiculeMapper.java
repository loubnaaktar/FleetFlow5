package org.example.fleetflow.mapper;

import org.example.fleetflow.DTO.VehiculeDTO;
import org.example.fleetflow.model.Vehicule;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel ="spring")
public interface VehiculeMapper {
   VehiculeDTO toDto(Vehicule vehicule);
   Vehicule toEntity(VehiculeDTO dto);
   Vehicule updateEntityFromDto(VehiculeDTO dto, @MappingTarget Vehicule entity);
}
