package tn.itbs.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import tn.itbs.project.entity.LigneCommande;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Integer> {
    
    @Transactional
    @Modifying
    @Query("DELETE FROM LigneCommande l WHERE l.commande.id = :commandeId")
    void deleteByCommandeId(int commandeId);
}