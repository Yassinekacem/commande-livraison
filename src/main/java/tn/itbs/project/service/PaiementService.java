package tn.itbs.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.itbs.project.dto.PaiementDTO;
import tn.itbs.project.entity.Commande;
import tn.itbs.project.entity.Paiement;
import tn.itbs.project.repository.CommandeRepository;
import tn.itbs.project.repository.PaiementRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    public List<PaiementDTO> getAll() {
        return paiementRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PaiementDTO getById(int id) {
        return paiementRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public PaiementDTO create(PaiementDTO dto) {
        Commande commande = commandeRepository.findById(dto.getCommandeId())
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID: " + dto.getCommandeId()));

        Paiement paiement = new Paiement();
        paiement.setCommande(commande);
        paiement.setDate(dto.getDate());
        paiement.setStatut(dto.getStatut());
        paiement.setMode(dto.getMode());

        return convertToDTO(paiementRepository.save(paiement));
    }

    public PaiementDTO update(int id, PaiementDTO dto) {
        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé avec l'ID: " + id));

        Commande commande = commandeRepository.findById(dto.getCommandeId())
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID: " + dto.getCommandeId()));

        paiement.setCommande(commande);
        paiement.setDate(dto.getDate());
        paiement.setStatut(dto.getStatut());
        paiement.setMode(dto.getMode());

        return convertToDTO(paiementRepository.save(paiement));
    }

    public void delete(int id) {
        if (!paiementRepository.existsById(id)) {
            throw new RuntimeException("Paiement non trouvé avec l'ID: " + id);
        }
        paiementRepository.deleteById(id);
    }

    private PaiementDTO convertToDTO(Paiement paiement) {
        PaiementDTO dto = new PaiementDTO();
        dto.setId(paiement.getId());
        dto.setCommandeId(paiement.getCommande().getId());
        dto.setDate(paiement.getDate());
        dto.setStatut(paiement.getStatut());
        dto.setMode(paiement.getMode());
        return dto;
    }
}