package tn.itbs.project.dto;

import lombok.Data;

@Data
public class LigneCommandeDTO {
    private int id;
    private int commandeId;
    private String produit;
    private int quantite;
    private double prixUnitaire;
}
