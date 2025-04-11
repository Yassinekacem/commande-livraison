package tn.itbs.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.itbs.project.dto.LigneCommandeDTO;
import tn.itbs.project.service.LigneCommandeService;

import java.util.List;

@RestController
@RequestMapping("/api/lignes")
public class LigneCommandeController {

    @Autowired
    private LigneCommandeService ligneCommandeService;

    @GetMapping
    public List<LigneCommandeDTO> getAll() {
        return ligneCommandeService.getAllLignes();
    }

    @GetMapping("/{id}")
    public LigneCommandeDTO getById(@PathVariable int id) {
        return ligneCommandeService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LigneCommandeDTO dto) {
        try {
            LigneCommandeDTO createdLigne = ligneCommandeService.createLigneCommande(dto);
            return ResponseEntity.ok(createdLigne);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody LigneCommandeDTO dto) {
        try {
            LigneCommandeDTO updatedLigne = ligneCommandeService.updateLigneCommande(id, dto);
            return ResponseEntity.ok(updatedLigne);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            ligneCommandeService.deleteLigneCommande(id);
            return ResponseEntity.ok("Ligne de commande supprimée avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Erreur lors de la suppression: " + e.getMessage());
        }
    }
}