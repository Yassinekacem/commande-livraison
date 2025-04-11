package tn.itbs.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    public LivraisonDTO create(@RequestBody LivraisonDTO dto) {
        return livraisonService.create(dto);
    }

    @PutMapping("/{id}")
    public LivraisonDTO update(@PathVariable int id, @RequestBody LivraisonDTO dto) {
        return livraisonService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        livraisonService.delete(id);
    }
}
