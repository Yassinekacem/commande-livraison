package tn.itbs.project.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Commande { 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id; 
	 
	 @ManyToOne
	    @JoinColumn(name = "client_id", nullable = false) 
	    private Client client;  
	 
	 
	   @OneToOne
	    @JoinColumn(name = "livraison_id")
	    private Livraison livraison;
	 
	 
	  private String date;
	    private String statut;
	    private double montantTotal;  
	    
	    
	    
	    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	    private List<LigneCommande> lignesCommande = new ArrayList<LigneCommande>();
}
