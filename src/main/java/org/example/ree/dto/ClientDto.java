package org.example.ree.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ClientDto {

    private Long id;

    @NotBlank(message = "Nom ne doit pas être vide")
    @Size(min = 2, max = 100, message = "Nom doit contenir entre 2 et 100 caractères")
    private String nom;

    @NotBlank(message = "Email ne doit pas être vide")
    @Email(message = "Email doit être valide")
    private String email;

    @NotBlank(message = "SIRET ne doit pas être vide")
    @Size(min = 14, max = 14, message = "SIRET doit contenir 14 caractères")
    private String siret;

    private LocalDate dateCreation;

}
