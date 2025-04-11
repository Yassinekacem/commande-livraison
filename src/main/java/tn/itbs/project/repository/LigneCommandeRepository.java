package tn.itbs.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.project.entity.LigneCommande;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Integer> {
}
