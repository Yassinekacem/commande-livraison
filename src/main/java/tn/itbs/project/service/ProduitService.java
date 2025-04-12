package tn.itbs.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.itbs.project.dto.ProduitDTO;
import tn.itbs.project.entity.Produit;
import tn.itbs.project.repository.ProduitRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

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
        return convertToDTO(produitRepository.save(produit));
    }

    public ProduitDTO updateProduit(int id, ProduitDTO dto) {
        Produit produit = produitRepository.findById(id).orElse(null);
        if (produit == null) {
            throw new RuntimeException("Produit non trouvé avec l'ID: " + id);
        }

        produit.setNom(dto.getNom());
        produit.setStockActuel(dto.getStockActuel());
        produit.setDescription(dto.getDescription());

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
        return dto;
    }
}
