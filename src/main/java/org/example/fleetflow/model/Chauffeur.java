package org.example.fleetflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Chauffeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String telephone;
    private String permisType;
    private Boolean disponible;


    @OneToMany(mappedBy = "chauffeur")
    List<Livraison> livraisons = new ArrayList<>();
}
