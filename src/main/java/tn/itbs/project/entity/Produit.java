package tn.itbs.project.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private int stockActuel;
    private String description;  
    
    
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<LigneCommande> ligneCommandes = new ArrayList<LigneCommande>();
    
    
}
