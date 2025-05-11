package tn.itbs.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.itbs.project.entity.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
}
