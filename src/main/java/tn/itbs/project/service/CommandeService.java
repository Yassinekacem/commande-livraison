package tn.itbs.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.itbs.project.dto.CommandeDTO;
import tn.itbs.project.entity.Client;
import tn.itbs.project.entity.Commande;
import tn.itbs.project.repository.ClientRepository;
import tn.itbs.project.repository.CommandeRepository;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<CommandeDTO> getAllCommandes() {
        return commandeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CommandeDTO getCommandeById(int id) {
        return commandeRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public CommandeDTO createCommande(CommandeDTO dto) {
        Client client = clientRepository.findById(dto.getClientId()).orElse(null);
        if (client == null) return null;

        Commande commande = new Commande();
        commande.setClient(client);
        commande.setDate(dto.getDate());
        commande.setStatut(dto.getStatut());
        commande.setMontantTotal(dto.getMontantTotal());

        return convertToDTO(commandeRepository.save(commande));
    }

    public CommandeDTO updateCommande(int id, CommandeDTO dto) {
        Commande commande = commandeRepository.findById(id).orElse(null);
        if (commande == null) return null;

        Client client = clientRepository.findById(dto.getClientId()).orElse(null);
        if (client == null) return null;

        commande.setClient(client);
        commande.setDate(dto.getDate());
        commande.setStatut(dto.getStatut());
        commande.setMontantTotal(dto.getMontantTotal());

        return convertToDTO(commandeRepository.save(commande));
    }

    public void deleteCommande(int id) {
        commandeRepository.deleteById(id);
    }

    private CommandeDTO convertToDTO(Commande commande) {
        CommandeDTO dto = new CommandeDTO();
        dto.setId(commande.getId());
        dto.setClientId(commande.getClient().getId());
        dto.setDate(commande.getDate());
        dto.setStatut(commande.getStatut());
        dto.setMontantTotal(commande.getMontantTotal());
        return dto;
    }
}
