package org.example.fleetflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChauffeurDTO implements Serializable {

    private Long id ;
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;
    @NotBlank(message = "Le type de permis est obligatoire")
    private String permisType;
    @NotNull(message = "Le statut disponible est obligatoire")
    private Boolean disponible;
//    private List<Livraison> livraisons ;
}
