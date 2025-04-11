package tn.itbs.project.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String email;
    private String adresse; 
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Commande> Commandes = new ArrayList<Commande>();
}
