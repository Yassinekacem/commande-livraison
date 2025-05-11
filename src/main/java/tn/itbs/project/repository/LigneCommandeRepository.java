package tn.itbs.project.repository;

import java.util.List;

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
    
    // Nouvelle méthode pour récupérer les lignes de commande par fournisseur
    @Query("SELECT lc FROM LigneCommande lc " +
           "JOIN lc.produit p " +
           "WHERE p.fournisseur.id = :fournisseurId")
    List<LigneCommande> findLignesByFournisseurId(int fournisseurId);
}