package tn.itbs.project.dto;

import lombok.Data;
import java.util.List;

@Data
public class TransporteurDTO {
    private int id;
    private String nom;
    private String telephone;
    private double note;
    private List<LivraisonDTO> livraisons;
}