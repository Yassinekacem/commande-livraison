package tn.itbs.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.itbs.project.dto.TransporteurDTO;
import tn.itbs.project.entity.Transporteur;
import tn.itbs.project.repository.TransporteurRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransporteurService {

    @Autowired
    private TransporteurRepository transporteurRepository;

    public List<TransporteurDTO> getAll() {
        return transporteurRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TransporteurDTO getById(int id) {
        return transporteurRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public TransporteurDTO create(TransporteurDTO dto) {
        Transporteur t = new Transporteur();
        t.setNom(dto.getNom());
        t.setTelephone(dto.getTelephone());
        t.setNote(dto.getNote());

        return convertToDTO(transporteurRepository.save(t));
    }

    public TransporteurDTO update(int id, TransporteurDTO dto) {
        Transporteur t = transporteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transporteur non trouvé avec l'ID: " + id));

        t.setNom(dto.getNom());
        t.setTelephone(dto.getTelephone());
        t.setNote(dto.getNote());

        return convertToDTO(transporteurRepository.save(t));
    }

    public void delete(int id) {
        if (!transporteurRepository.existsById(id)) {
            throw new RuntimeException("Transporteur non trouvé avec l'ID: " + id);
        }
        transporteurRepository.deleteById(id);
    }

    private TransporteurDTO convertToDTO(Transporteur t) {
        TransporteurDTO dto = new TransporteurDTO();
        dto.setId(t.getId());
        dto.setNom(t.getNom());
        dto.setTelephone(t.getTelephone());
        dto.setNote(t.getNote());
        return dto;
    }
}