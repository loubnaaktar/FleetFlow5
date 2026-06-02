package org.example.fleetflow.Repository;

import org.example.fleetflow.model.Vehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule,Long> {

    Page<Vehicule> findByStatut(Vehicule.StatutVehicule statut, Pageable pageable);
    Page<Vehicule> findByCapaciteGreaterThan(Integer capacite,Pageable pageable);
    List<Vehicule> findByStatutAndCapaciteGreaterThan(Vehicule.StatutVehicule statut,Integer capacite);

}
