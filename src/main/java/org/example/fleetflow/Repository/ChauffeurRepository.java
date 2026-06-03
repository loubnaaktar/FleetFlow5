package org.example.fleetflow.Repository;

import org.example.fleetflow.model.Chauffeur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChauffeurRepository extends JpaRepository<Chauffeur, Long> {


    Page<Chauffeur> findByDisponible(boolean disponible, Pageable pageable);


}
