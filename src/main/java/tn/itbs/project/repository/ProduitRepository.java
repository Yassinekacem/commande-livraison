package tn.itbs.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.project.entity.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
}
