package tn.itbs.project.dto;

import lombok.Data;

@Data
public class LigneCommandeDTO {
    private int id;
    private int commandeId;
    private int produitId;
    private int quantite;
    private double prixUnitaire;
}
