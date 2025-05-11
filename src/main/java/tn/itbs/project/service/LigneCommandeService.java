package tn.itbs.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.itbs.project.dto.LigneCommandeDTO;
import tn.itbs.project.entity.Commande;
import tn.itbs.project.entity.LigneCommande;
import tn.itbs.project.entity.Produit;
import tn.itbs.project.repository.CommandeRepository;
import tn.itbs.project.repository.LigneCommandeRepository;
import tn.itbs.project.repository.ProduitRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LigneCommandeService {

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ProduitRepository produitRepository;

    public List<LigneCommandeDTO> getAllLignes() {
        return ligneCommandeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LigneCommandeDTO getById(int id) {
        return ligneCommandeRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    } 
    
    
 // LigneCommandeService.java

    public List<LigneCommandeDTO> getLignesCommandesByFournisseur(int fournisseurId) {
        return ligneCommandeRepository.findLignesByFournisseurId(fournisseurId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    public LigneCommandeDTO createLigneCommande(LigneCommandeDTO dto) {
        Commande commande = commandeRepository.findById(dto.getCommandeId())
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID: " + dto.getCommandeId()));

        Produit produit = produitRepository.findById(dto.getProduitId())
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + dto.getProduitId()));

        LigneCommande ligne = new LigneCommande();
        ligne.setCommande(commande);
        ligne.setProduit(produit);
        ligne.setQuantite(dto.getQuantite());
        ligne.setPrixUnitaire(dto.getPrixUnitaire());

        return convertToDTO(ligneCommandeRepository.save(ligne));
    }

    public LigneCommandeDTO updateLigneCommande(int id, LigneCommandeDTO dto) {
        LigneCommande ligne = ligneCommandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ligne de commande non trouvée avec l'ID: " + id));

        Commande commande = commandeRepository.findById(dto.getCommandeId())
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID: " + dto.getCommandeId()));

        Produit produit = produitRepository.findById(dto.getProduitId())
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + dto.getProduitId()));

        ligne.setCommande(commande);
        ligne.setProduit(produit);
        ligne.setQuantite(dto.getQuantite());
        ligne.setPrixUnitaire(dto.getPrixUnitaire());

        return convertToDTO(ligneCommandeRepository.save(ligne));
    } 

    public void deleteLigneCommande(int id) {
        if (!ligneCommandeRepository.existsById(id)) {
            throw new RuntimeException("Ligne de commande non trouvée avec l'ID: " + id);
        }
        ligneCommandeRepository.deleteById(id);
    }
 
    private LigneCommandeDTO convertToDTO(LigneCommande ligne) {
        LigneCommandeDTO dto = new LigneCommandeDTO();
        dto.setId(ligne.getId());
        dto.setCommandeId(ligne.getCommande().getId());
        dto.setProduitId(ligne.getProduit().getId());
        dto.setQuantite(ligne.getQuantite());
        dto.setPrixUnitaire(ligne.getPrixUnitaire());
        return dto;
    }
}
