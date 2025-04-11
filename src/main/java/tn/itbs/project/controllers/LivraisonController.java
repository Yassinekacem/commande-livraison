package tn.itbs.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.itbs.project.dto.LivraisonDTO;
import tn.itbs.project.service.LivraisonService;

import java.util.List;

@RestController
@RequestMapping("/api/livraisons")
public class LivraisonController {

    @Autowired
    private LivraisonService livraisonService;

    @GetMapping
    public List<LivraisonDTO> getAll() {
        return livraisonService.getAll();
    }

    @GetMapping("/{id}")
    public LivraisonDTO getById(@PathVariable int id) {
        return livraisonService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LivraisonDTO dto) {
        try {
            LivraisonDTO createdLivraison = livraisonService.create(dto);
            return ResponseEntity.ok(createdLivraison);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody LivraisonDTO dto) {
        try {
            LivraisonDTO updatedLivraison = livraisonService.update(id, dto);
            return ResponseEntity.ok(updatedLivraison);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            livraisonService.delete(id);
            return ResponseEntity.ok("Livraison supprimée avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Erreur lors de la suppression: " + e.getMessage());
        }
    }
}