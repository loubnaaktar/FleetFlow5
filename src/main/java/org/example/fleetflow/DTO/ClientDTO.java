package org.example.fleetflow.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
    public class ClientDTO {
        private Long id;
    @NotBlank(message = "Le nom est obligatoire")
        private String nom;
    @Email(message = "email est obligatoire")
        private String email;
    @NotBlank(message = "La ville est obligatoire")
        private String ville;
    @NotBlank(message = "telephone est obligatoire")
    @Size(min = 10, max = 10, message = "numero doit étre mois de 10")
        private String  telephone;
    }

