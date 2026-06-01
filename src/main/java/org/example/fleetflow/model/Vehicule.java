package org.example.fleetflow.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="vehicule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String matricule;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeVehicule type;
    private Integer   capacite;
   @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutVehicule statut= StatutVehicule.DISPONIBLE;
//   @OneToOne(mappedBy = "vehicule")
//    private Livraison livraison;
   public enum TypeVehicule{
       CAMION,FOURGON,CAMIONETTE
   }
   public enum StatutVehicule{
       DISPONIBLE,EN_LIVRAISON,MAINTENANCE
   }
}
