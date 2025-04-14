package tn.itbs.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.itbs.project.dto.LivraisonDTO;
import tn.itbs.project.entity.Commande;
import tn.itbs.project.entity.LigneCommande;
import tn.itbs.project.entity.Livraison;
import tn.itbs.project.entity.Produit;
import tn.itbs.project.entity.Transporteur;
import tn.itbs.project.repository.CommandeRepository;
import tn.itbs.project.repository.LivraisonRepository;
import tn.itbs.project.repository.TransporteurRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private TransporteurRepository transporteurRepository;

    public List<LivraisonDTO> getAll() {
        return livraisonRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LivraisonDTO getById(int id) {
        return livraisonRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public LivraisonDTO create(LivraisonDTO dto) {
        Commande commande = commandeRepository.findById(dto.getCommandeId())
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID: " + dto.getCommandeId()));
        
        Transporteur transporteur = transporteurRepository.findById(dto.getTransporteurId())
                .orElseThrow(() -> new RuntimeException("Transporteur non trouvé avec l'ID: " + dto.getTransporteurId()));

        Livraison livraison = new Livraison();
        livraison.setCommande(commande);
        livraison.setTransporteur(transporteur);
        livraison.setDateLivraison(dto.getDateLivraison());
        livraison.setCout(dto.getCout());
        livraison.setStatut(dto.getStatut());

        // 🚨 Mettre à jour le statut de la commande
        commande.setStatut("EN_COURS_DE_LIVRAISON");
        commandeRepository.save(commande);

        return convertToDTO(livraisonRepository.save(livraison));
    }


    public LivraisonDTO update(int id, LivraisonDTO dto) {
        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livraison non trouvée avec l'ID: " + id));

        Commande commande = commandeRepository.findById(dto.getCommandeId())
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID: " + dto.getCommandeId()));
        
        Transporteur transporteur = transporteurRepository.findById(dto.getTransporteurId())
                .orElseThrow(() -> new RuntimeException("Transporteur non trouvé avec l'ID: " + dto.getTransporteurId()));

        livraison.setCommande(commande);
        livraison.setTransporteur(transporteur);
        livraison.setDateLivraison(dto.getDateLivraison());
        livraison.setCout(dto.getCout());
        livraison.setStatut(dto.getStatut());

        // 🚨 Si la livraison est livrée
        if ("LIVREE".equalsIgnoreCase(dto.getStatut())) {
            commande.setStatut("REÇUE");

            // 🔄 Mise à jour du stock produit
            if (commande.getLignesCommande() != null) {
                for (LigneCommande ligne : commande.getLignesCommande()) {
                    Produit produit = ligne.getProduit();
                    int nouvelleQuantite = produit.getStockActuel() - ligne.getQuantite();
                    produit.setStockActuel(nouvelleQuantite);
                }
            }
        }

        commandeRepository.save(commande); // Sauvegarde commande (statut)
        return convertToDTO(livraisonRepository.save(livraison)); // Sauvegarde livraison
    }



    public void delete(int id) {
        if (!livraisonRepository.existsById(id)) {
            throw new RuntimeException("Livraison non trouvée avec l'ID: " + id);
        }
        livraisonRepository.deleteById(id);
    }

    private LivraisonDTO convertToDTO(Livraison livraison) {
        LivraisonDTO dto = new LivraisonDTO();
        dto.setId(livraison.getId());
        dto.setCommandeId(livraison.getCommande().getId());
        dto.setTransporteurId(livraison.getTransporteur().getId());
        dto.setDateLivraison(livraison.getDateLivraison());
        dto.setCout(livraison.getCout());
        dto.setStatut(livraison.getStatut());
        return dto;
    }
}