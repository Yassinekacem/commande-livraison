package tn.itbs.project.service;

import tn.itbs.project.dto.ClientDTO;
import tn.itbs.project.entity.Client;
import tn.itbs.project.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO getClientById(int id) {
        return clientRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public ClientDTO createClient(ClientDTO dto) {
        Client client = new Client();
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        client.setAdresse(dto.getAdresse());
        Client saved = clientRepository.save(client);
        return convertToDTO(saved);
    }

    public ClientDTO updateClient(int id, ClientDTO dto) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) return null;

        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        client.setAdresse(dto.getAdresse());

        return convertToDTO(clientRepository.save(client));
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

    private ClientDTO convertToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setEmail(client.getEmail());
        dto.setAdresse(client.getAdresse());
        return dto;
    }
}
