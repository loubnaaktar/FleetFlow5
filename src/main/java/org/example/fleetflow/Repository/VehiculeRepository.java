package org.example.fleetflow.Repository;

import org.example.fleetflow.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule,Long> {

    List<Vehicule> findByStatut(Vehicule.StatutVehicule statut);
    List<Vehicule> findByCapaciteGreaterThan(Integer capacite);
    List<Vehicule> findByStatutAndCapaciteGreaterThan(Vehicule.StatutVehicule statut,Integer capacite);

}
