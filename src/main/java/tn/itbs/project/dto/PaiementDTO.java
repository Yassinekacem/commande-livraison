package tn.itbs.project.dto;

import lombok.Data;

@Data
public class PaiementDTO {
    private int id;
    private int commandeId;
    private String date;
    private String mode;
}
