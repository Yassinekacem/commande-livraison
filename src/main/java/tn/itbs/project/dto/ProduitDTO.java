package tn.itbs.project.dto;

import lombok.Data;

@Data
public class ProduitDTO {
    private int id;
    private String nom;
    private int stockActuel;
    private String description;
}
