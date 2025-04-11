package tn.itbs.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.itbs.project.dto.CommandeDTO;
import tn.itbs.project.service.CommandeService;
import org.springframework.http.ResponseEntity; 
import org.springframework.http.HttpStatus;


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
    public ResponseEntity<?> createCommande(@RequestBody CommandeDTO dto) {
        try {
            CommandeDTO createdCommande = commandeService.createCommande(dto);
            return ResponseEntity.ok(createdCommande);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCommande(@PathVariable int id, @RequestBody CommandeDTO dto) {
        try {
            CommandeDTO updatedCommande = commandeService.updateCommande(id, dto);
            return ResponseEntity.ok(updatedCommande);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommande(@PathVariable int id) {
        try {
            commandeService.deleteCommande(id);
            return ResponseEntity.ok("Commande supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Erreur lors de la suppression: " + e.getMessage());
        }
    }
}
