package tn.itbs.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.itbs.project.dto.FournisseurDTO;
import tn.itbs.project.entity.Fournisseur;
import tn.itbs.project.repository.FournisseurRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    public List<FournisseurDTO> getAllFournisseurs() {
        return fournisseurRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FournisseurDTO getFournisseurById(int id) {
        return fournisseurRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec l'ID: " + id));
    }

    public FournisseurDTO createFournisseur(FournisseurDTO dto) {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setNom(dto.getNom());
        fournisseur.setAdresse(dto.getAdresse());
        return convertToDTO(fournisseurRepository.save(fournisseur));
    }

    public FournisseurDTO updateFournisseur(int id, FournisseurDTO dto) {
        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec l'ID: " + id));

        fournisseur.setNom(dto.getNom());
        fournisseur.setAdresse(dto.getAdresse());

        return convertToDTO(fournisseurRepository.save(fournisseur));
    }

    public void deleteFournisseur(int id) {
        if (!fournisseurRepository.existsById(id)) {
            throw new RuntimeException("Fournisseur non trouvé avec l'ID: " + id);
        }
        fournisseurRepository.deleteById(id);
    }

    private FournisseurDTO convertToDTO(Fournisseur fournisseur) {
        FournisseurDTO dto = new FournisseurDTO();
        dto.setId(fournisseur.getId());
        dto.setNom(fournisseur.getNom());
        dto.setAdresse(fournisseur.getAdresse());
        return dto;
    }
}
