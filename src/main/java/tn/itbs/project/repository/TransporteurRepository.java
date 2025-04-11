package tn.itbs.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.project.entity.Transporteur;

public interface TransporteurRepository extends JpaRepository<Transporteur, Integer> {
}
