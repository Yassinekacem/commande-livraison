package tn.itbs.project.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Fournisseur { 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id; 
	 
	 private String nom; 
	 private String adresse;  
	 
	  @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	    private List<Produit> produits = new ArrayList<Produit>();


}
