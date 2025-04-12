package tn.itbs.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.itbs.project.dto.PaiementDTO;
import tn.itbs.project.service.PaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    @GetMapping
    public List<PaiementDTO> getAll() {
        return paiementService.getAll();
    }

    @GetMapping("/{id}")
    public PaiementDTO getById(@PathVariable int id) {
        return paiementService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PaiementDTO dto) {
        try {
            PaiementDTO createdPaiement = paiementService.create(dto);
            return ResponseEntity.ok(createdPaiement);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody PaiementDTO dto) {
        try {
            PaiementDTO updatedPaiement = paiementService.update(id, dto);
            return ResponseEntity.ok(updatedPaiement);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            paiementService.delete(id);
            return ResponseEntity.ok("Paiement supprimé avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Erreur lors de la suppression: " + e.getMessage());
        }
    }
}