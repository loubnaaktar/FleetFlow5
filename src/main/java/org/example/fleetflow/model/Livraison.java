package org.example.fleetflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateLivraison;
    private String adresseDepart;
    private String adresseDestination;
    @Enumerated(EnumType.STRING)
    private StatutLivraison statut;

    @ManyToOne
    private Chauffeur chauffeur;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Vehicule vehicule;

    public enum StatutLivraison{
        ENATTENTE,ENCOURS, LIVREE
    }
}
