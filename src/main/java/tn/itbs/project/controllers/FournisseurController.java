package tn.itbs.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.itbs.project.dto.FournisseurDTO;
import tn.itbs.project.service.FournisseurService;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping
    public List<FournisseurDTO> getAll() {
        return fournisseurService.getAllFournisseurs();
    }

    @GetMapping("/{id}")
    public FournisseurDTO getById(@PathVariable int id) {
        return fournisseurService.getFournisseurById(id);
    }

    @PostMapping
    public ResponseEntity<FournisseurDTO> create(@RequestBody FournisseurDTO dto) {
        return ResponseEntity.ok(fournisseurService.createFournisseur(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FournisseurDTO> update(@PathVariable int id, @RequestBody FournisseurDTO dto) {
        return ResponseEntity.ok(fournisseurService.updateFournisseur(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        fournisseurService.deleteFournisseur(id);
        return ResponseEntity.ok("Fournisseur supprimé avec succès");
    }
}
