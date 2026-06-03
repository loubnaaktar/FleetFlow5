package org.example.fleetflow.Repository;

import org.example.fleetflow.model.Livraison;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
    Page<Livraison> findByStatut(Livraison.StatutLivraison statut, Pageable pageable);
   Page<Livraison> findByClientId(Long id,Pageable pageable);


   @Query("select l from Livraison l where l.dateLivraison BETWEEN :date1 AND :date2")
   List<Livraison> findLivraisonBetweenTwoDate(LocalDateTime date1,LocalDateTime date2);
   @Query("select l from Livraison l where l.adresseDestination = :ville")
   List<Livraison> findLivraisonByVille(String ville);

   Page<Livraison> findAll(Pageable pageable);

   @Query("select l from Livraison l where l.chauffeur.id = :id")
   Page<Livraison> findLivraisonsByChauffeur_Id(Long id, Pageable pageable);
}
