package tn.itbs.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.project.entity.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Integer> {
}
