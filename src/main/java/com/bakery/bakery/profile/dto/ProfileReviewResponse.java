package com.bakery.bakery.profile.dto;

import lombok.Data;

@Data
public class ProfileReviewResponse {
    private Long id;
    private String dni;
    private String docDNI;
    private String ruc;
    private String docRUC;
    private String docSanitation;
    private String permMunicipa;
    private String license;
    private Long process;
    private String message;
    private String bakerPassword;
    private String bakerUsername;
}
