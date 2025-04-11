package tn.itbs.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Livraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "transporteur_id", nullable = false)
    private Transporteur transporteur;

    private String dateLivraison;
    private double cout;
    private String statut;
}
