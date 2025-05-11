package tn.itbs.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.itbs.project.dto.ProduitDTO;
import tn.itbs.project.entity.Fournisseur;
import tn.itbs.project.entity.Produit;
import tn.itbs.project.repository.FournisseurRepository;
import tn.itbs.project.repository.ProduitRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    public List<ProduitDTO> getAllProduits() {
        return produitRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProduitDTO getProduitById(int id) {
        return produitRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public ProduitDTO createProduit(ProduitDTO dto) {
        Produit produit = new Produit();
        produit.setNom(dto.getNom());
        produit.setStockActuel(dto.getStockActuel());
        produit.setDescription(dto.getDescription());

        Fournisseur fournisseur = fournisseurRepository.findById(dto.getFournisseurId())
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec l'ID: " + dto.getFournisseurId()));
        produit.setFournisseur(fournisseur);

        return convertToDTO(produitRepository.save(produit));
    }

    public ProduitDTO updateProduit(int id, ProduitDTO dto) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + id));

        produit.setNom(dto.getNom());
        produit.setStockActuel(dto.getStockActuel());
        produit.setDescription(dto.getDescription());

        Fournisseur fournisseur = fournisseurRepository.findById(dto.getFournisseurId())
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec l'ID: " + dto.getFournisseurId()));
        produit.setFournisseur(fournisseur);

        return convertToDTO(produitRepository.save(produit));
    }

    public void deleteProduit(int id) {
        if (!produitRepository.existsById(id)) {
            throw new RuntimeException("Produit non trouvé avec l'ID: " + id);
        }
        produitRepository.deleteById(id);
    }

    private ProduitDTO convertToDTO(Produit produit) {
        ProduitDTO dto = new ProduitDTO();
        dto.setId(produit.getId());
        dto.setNom(produit.getNom());
        dto.setStockActuel(produit.getStockActuel());
        dto.setDescription(produit.getDescription());
        dto.setFournisseurId(produit.getFournisseur().getId());
        return dto;
    }
}
