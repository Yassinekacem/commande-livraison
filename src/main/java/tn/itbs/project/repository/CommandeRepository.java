package tn.itbs.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.project.entity.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer> { 
    List<Commande> findByClientId(int clientId);

}
