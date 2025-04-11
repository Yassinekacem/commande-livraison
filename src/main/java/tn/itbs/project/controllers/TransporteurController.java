package tn.itbs.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    public TransporteurDTO create(@RequestBody TransporteurDTO dto) {
        return transporteurService.create(dto);
    }

    @PutMapping("/{id}")
    public TransporteurDTO update(@PathVariable int id, @RequestBody TransporteurDTO dto) {
        return transporteurService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        transporteurService.delete(id);
    }
}
