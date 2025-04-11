package tn.itbs.project.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Transporteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String telephone;
    private double note;   
    
    @OneToMany(mappedBy = "transporteur", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Livraison> livraisons = new ArrayList<Livraison>();
}
