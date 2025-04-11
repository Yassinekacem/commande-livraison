package tn.itbs.project.controllers;

import tn.itbs.project.dto.ClientDTO;
import tn.itbs.project.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable int id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO client) {
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public ClientDTO updateClient(@PathVariable int id, @RequestBody ClientDTO client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
        return "Client supprimé avec succès";
    }
}
