package org.example.ree.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ligne_factures")
public class LigneFacture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Description ne doit pas être vide")
    private String description;

    @NotNull(message = "Quantité ne doit pas être vide")
    @Positive(message = "Quantité doit être positive")
    private Double quantite;

    @NotNull(message = "Prix unitaire HT ne doit pas être vide")
    @Positive(message = "Prix unitaire HT doit être positive")
    private Double prixUnitaireHT;

    @NotNull(message = "Taux TVA ne doit pas être vide")
    private Double tauxTVA;

    public double getTotalHT() {
        return quantite * prixUnitaireHT;
    }

    public double getTotalTVA() {
        return getTotalHT() * tauxTVA;
    }

    public double getTotalTTC() {
        return getTotalHT() + getTotalTVA();
    }
}
