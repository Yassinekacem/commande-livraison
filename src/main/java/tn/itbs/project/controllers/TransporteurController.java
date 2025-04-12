package tn.itbs.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.itbs.project.dto.TransporteurDTO;
import tn.itbs.project.service.TransporteurService;

import java.util.List;

@RestController
@RequestMapping("/api/transporteurs")
public class TransporteurController {

    @Autowired
    private TransporteurService transporteurService;

    @GetMapping
    public List<TransporteurDTO> getAll() {
        return transporteurService.getAll();
    }

    @GetMapping("/{id}")
    public TransporteurDTO getById(@PathVariable int id) {
        return transporteurService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TransporteurDTO dto) {
        try {
            TransporteurDTO createdTransporteur = transporteurService.create(dto);
            return ResponseEntity.ok(createdTransporteur);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody TransporteurDTO dto) {
        try {
            TransporteurDTO updatedTransporteur = transporteurService.update(id, dto);
            return ResponseEntity.ok(updatedTransporteur);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            transporteurService.delete(id);
            return ResponseEntity.ok("Transporteur supprimé avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Erreur lors de la suppression: " + e.getMessage());
        }
    }
}