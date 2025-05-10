package tn.itbs.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    private String date;
    private String mode;  
}
