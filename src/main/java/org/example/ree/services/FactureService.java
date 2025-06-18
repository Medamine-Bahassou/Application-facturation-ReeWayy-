package org.example.ree.services;

import org.example.ree.models.Facture;
import org.example.ree.repositories.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;

    // récupère toutes les factures. c'est comme avoir un aperçu de toutes les factures émises.
    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    // trouve une facture par son identifiant. pratique pour retrouver une facture spécifique.
    public Optional<Facture> getFactureById(Long id) {
        return factureRepository.findById(id);
    }

    // crée une nouvelle facture. on enregistre une nouvelle transaction.
    public Facture createFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    // met à jour les informations d'une facture existante. on corrige ou modifie les détails d'une facture.
    public Facture updateFacture(Long id, Facture facture) {
        return factureRepository.findById(id)
                .map(existingFacture -> {
                    facture.setId(id);
                    return factureRepository.save(facture);
                })
                .orElse(null);
    }

    // supprime une facture. on retire une facture de nos registres.
    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }

    // recherche des factures en fonction d'un client ou d'une date. ça aide à filtrer les factures pour trouver ce qu'on cherche.
    public List<Facture> findFactures(Long clientId, LocalDate date) {
        List<Facture> factures = factureRepository.findAll();

        return factures.stream()
                .filter(facture -> {
                    boolean matchesClientId = (clientId == null || facture.getClientId().equals(clientId));
                    boolean matchesDate = true;
                    if (date != null) {
                        if (facture.getDate() != null) {
                            matchesDate = facture.getDate().isEqual(date);
                        } else {
                            matchesDate = false; // si une date de recherche est renseignée mais que la date de facture est vide, ça ne correspond pas
                        }
                    }
                    return matchesClientId && matchesDate;
                })
                .collect(Collectors.toList());
    }
}
