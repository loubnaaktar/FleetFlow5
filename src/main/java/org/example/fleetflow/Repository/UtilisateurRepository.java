package org.example.fleetflow.Repository;

import org.example.fleetflow.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>  {
    Utilisateur findByEmail(String email);
}
