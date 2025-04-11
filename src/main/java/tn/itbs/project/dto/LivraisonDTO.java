package tn.itbs.project.dto;

import lombok.Data;

@Data
public class LivraisonDTO {
    private int id;
    private int commandeId;
    private int transporteurId;
    private String dateLivraison;
    private double cout;
    private String statut;
}

