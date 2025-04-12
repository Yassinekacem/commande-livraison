package tn.itbs.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.itbs.project.dto.ProduitDTO;
import tn.itbs.project.service.ProduitService;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public List<ProduitDTO> getAll() {
        return produitService.getAllProduits();
    }

    @GetMapping("/{id}")
    public ProduitDTO getById(@PathVariable int id) {
        return produitService.getProduitById(id);
    }

    @PostMapping
    public ResponseEntity<ProduitDTO> create(@RequestBody ProduitDTO dto) {
        return ResponseEntity.ok(produitService.createProduit(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitDTO> update(@PathVariable int id, @RequestBody ProduitDTO dto) {
        return ResponseEntity.ok(produitService.updateProduit(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        produitService.deleteProduit(id);
        return ResponseEntity.ok("Produit supprimé avec succès");
    }
}
