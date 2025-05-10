package tn.itbs.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.itbs.project.dto.CommandeDTO;
import tn.itbs.project.dto.LigneCommandeDTO;
import tn.itbs.project.entity.*;
import tn.itbs.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

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

    @Transactional
    public CommandeDTO createCommande(CommandeDTO dto) {
        // Validation des données
        if (dto.getLignes() == null || dto.getLignes().isEmpty()) {
            throw new RuntimeException("La commande doit contenir au moins une ligne");
        }

        // Récupération du client
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        // Création de la commande
        Commande commande = new Commande();
        commande.setClient(client);
        commande.setDate(dto.getDate());
        commande.setStatut(dto.getStatut());

        // Gestion des lignes de commande
        List<LigneCommande> lignes = new ArrayList<>();
        double montantTotal = 0;

        for (LigneCommandeDTO ligneDto : dto.getLignes()) {
            Produit produit = produitRepository.findById(ligneDto.getProduitId())
                    .orElseThrow(() -> new RuntimeException("Produit ID " + ligneDto.getProduitId() + " non trouvé"));

            LigneCommande ligne = new LigneCommande();
            ligne.setProduit(produit);
            ligne.setQuantite(ligneDto.getQuantite());
            ligne.setPrixUnitaire(ligneDto.getPrixUnitaire());
            ligne.setCommande(commande);
            
            lignes.add(ligne);
            montantTotal += ligne.getQuantite() * ligne.getPrixUnitaire();
        }

        commande.setMontantTotal(montantTotal);
        commande.setLignesCommande(lignes);

        // Sauvegarde
        Commande savedCommande = commandeRepository.save(commande);
        return convertToDTO(savedCommande);
    }

    @Transactional
    public CommandeDTO updateCommande(int id, CommandeDTO dto) {
        // Récupération de la commande existante
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        // Récupération du client
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        // Mise à jour des informations de base
        commande.setClient(client);
        commande.setDate(dto.getDate());
        commande.setStatut(dto.getStatut());

        ligneCommandeRepository.deleteByCommandeId(commande.getId());

        List<LigneCommande> lignes = new ArrayList<>();
        double montantTotal = 0;

        for (LigneCommandeDTO ligneDto : dto.getLignes()) {
            Produit produit = produitRepository.findById(ligneDto.getProduitId())
                    .orElseThrow(() -> new RuntimeException("Produit ID " + ligneDto.getProduitId() + " non trouvé"));

            LigneCommande ligne = new LigneCommande();
            ligne.setProduit(produit);
            ligne.setQuantite(ligneDto.getQuantite());
            ligne.setPrixUnitaire(ligneDto.getPrixUnitaire());
            ligne.setCommande(commande);
            
            lignes.add(ligne);
            montantTotal += ligne.getQuantite() * ligne.getPrixUnitaire();
        }

        commande.setMontantTotal(montantTotal);
        commande.setLignesCommande(lignes);

        // Sauvegarde
        Commande updatedCommande = commandeRepository.save(commande);
        return convertToDTO(updatedCommande);
    }

    @Transactional
    public void deleteCommande(int id) {
        // Vérification existence de la commande
        if (!commandeRepository.existsById(id)) {
            throw new RuntimeException("Commande non trouvée");
        }
        
        // Suppression en cascade grâce à l'annotation dans l'entité
        commandeRepository.deleteById(id);
    } 
    
    
    public List<CommandeDTO> getCommandesByClientId(int clientId) {
        return commandeRepository.findByClientId(clientId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private CommandeDTO convertToDTO(Commande commande) {
        CommandeDTO dto = new CommandeDTO();
        dto.setId(commande.getId());
        dto.setClientId(commande.getClient().getId());
        dto.setDate(commande.getDate());
        dto.setStatut(commande.getStatut());
        dto.setMontantTotal(commande.getMontantTotal());

        // Conversion des lignes
        if (commande.getLignesCommande() != null) {
            List<LigneCommandeDTO> lignes = commande.getLignesCommande()
                    .stream()
                    .map(this::convertLigneToDTO)
                    .collect(Collectors.toList());
            dto.setLignes(lignes);
        }

        return dto;
    }

    private LigneCommandeDTO convertLigneToDTO(LigneCommande ligne) {
        LigneCommandeDTO dto = new LigneCommandeDTO();
        dto.setProduitId(ligne.getProduit().getId());
        dto.setQuantite(ligne.getQuantite());
        dto.setPrixUnitaire(ligne.getPrixUnitaire());
        return dto;
    }
}