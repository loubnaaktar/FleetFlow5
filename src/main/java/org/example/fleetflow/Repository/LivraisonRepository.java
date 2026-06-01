package org.example.fleetflow.Repository;

import org.example.fleetflow.model.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
    List<Livraison> findByStatut(Livraison.StatutLivraison statut);
   List<Livraison> findByClientId(Long id);


   @Query("select l from Livraison l where l.dateLivraison BETWEEN :date1 AND :date2")
   List<Livraison> findLivraisonBetweenTwoDate(LocalDateTime date1,LocalDateTime date2);
   @Query("select l from Livraison l where l.adresseDestination = :ville")
   List<Livraison> findLivraisonByVille(String ville);

}
