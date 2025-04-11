package tn.itbs.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.itbs.project.dto.LivraisonDTO;
import tn.itbs.project.entity.Commande;
import tn.itbs.project.entity.Livraison;
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
        Commande commande = commandeRepository.findById(dto.getCommandeId()).orElse(null);
        Transporteur transporteur = transporteurRepository.findById(dto.getTransporteurId()).orElse(null);
        if (commande == null || transporteur == null) return null;

        Livraison livraison = new Livraison();
        livraison.setCommande(commande);
        livraison.setTransporteur(transporteur);
        livraison.setDateLivraison(dto.getDateLivraison());
        livraison.setCout(dto.getCout());
        livraison.setStatut(dto.getStatut());

        return convertToDTO(livraisonRepository.save(livraison));
    }

    public LivraisonDTO update(int id, LivraisonDTO dto) {
        Livraison livraison = livraisonRepository.findById(id).orElse(null);
        if (livraison == null) return null;

        Commande commande = commandeRepository.findById(dto.getCommandeId()).orElse(null);
        Transporteur transporteur = transporteurRepository.findById(dto.getTransporteurId()).orElse(null);
        if (commande == null || transporteur == null) return null;

        livraison.setCommande(commande);
        livraison.setTransporteur(transporteur);
        livraison.setDateLivraison(dto.getDateLivraison());
        livraison.setCout(dto.getCout());
        livraison.setStatut(dto.getStatut());

        return convertToDTO(livraisonRepository.save(livraison));
    }

    public void delete(int id) {
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
