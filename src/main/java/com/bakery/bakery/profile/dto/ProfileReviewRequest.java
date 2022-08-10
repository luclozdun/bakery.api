package com.bakery.bakery.profile.dto;

import lombok.Data;

@Data
public class ProfileReviewRequest {
    private Long customerId;
    private String dni;
    private String docDNI;
    private String ruc;
    private String docRUC;
    private String docSanitation;
    private String permMunicipa;
    private String license;
    private String location;
    private String cost;
    private String nameBakery;
}