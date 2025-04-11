package tn.itbs.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.project.entity.Livraison;

public interface LivraisonRepository extends JpaRepository<Livraison, Integer> {
}
