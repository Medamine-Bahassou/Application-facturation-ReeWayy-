package org.example.ree.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "factures")
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Client ID ne doit pas être vide")
    private Long clientId;

    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "facture_id")
    private List<LigneFacture> lignes;

    public double getTotalHT() {
        return lignes.stream().mapToDouble(LigneFacture::getTotalHT).sum();
    }

    public double getTotalTVA() {
        return lignes.stream().mapToDouble(LigneFacture::getTotalTVA).sum();
    }

    public double getTotalTTC() {
        return lignes.stream().mapToDouble(LigneFacture::getTotalTTC).sum();
    }
}
