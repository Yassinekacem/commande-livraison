package tn.itbs.project.dto;

import lombok.Data;
import java.util.List;

@Data
public class CommandeDTO {
    private int id;
    private int clientId;
    private String date;
    private String statut;
    private double montantTotal;
    
    private List<LigneCommandeDTO> lignes;
}
