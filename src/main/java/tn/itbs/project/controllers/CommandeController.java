package tn.itbs.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.itbs.project.dto.CommandeDTO;
import tn.itbs.project.service.CommandeService;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public List<CommandeDTO> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public CommandeDTO getCommandeById(@PathVariable int id) {
        return commandeService.getCommandeById(id);
    }

    @PostMapping
    public CommandeDTO createCommande(@RequestBody CommandeDTO dto) {
        return commandeService.createCommande(dto);
    }

    @PutMapping("/{id}")
    public CommandeDTO updateCommande(@PathVariable int id, @RequestBody CommandeDTO dto) {
        return commandeService.updateCommande(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable int id) {
        commandeService.deleteCommande(id);
    }
}
