package org.example.ree.services;

import org.example.ree.models.Client;
import org.example.ree.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // récupère tous les clients. c'est comme demander la liste complète de nos clients.
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // trouve un client par son identifiant. utile si on cherche un client spécifique.
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    // crée un nouveau client. on ajoute un nouveau client à notre base de données.
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // met à jour les informations d'un client existant. on modifie les détails d'un client qu'on connaît déjà.
    public Client updateClient(Long id, Client client) {
        return clientRepository.findById(id)
                .map(existingClient -> {
                    client.setId(id);
                    return clientRepository.save(client);
                })
                .orElse(null);
    }

    // supprime un client. on retire un client de notre système.
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
