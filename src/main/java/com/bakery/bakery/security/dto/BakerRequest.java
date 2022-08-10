package com.bakery.bakery.security.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BakerRequest extends PersonRequest {
    private String dni;
    private String docDNI;
    private String ruc;
    private String docRUC;
    private String docSanitation;
    private String permMunicipa;
    private String license;
    private String location;
    private String nameBakery;
    private String cost;
}
