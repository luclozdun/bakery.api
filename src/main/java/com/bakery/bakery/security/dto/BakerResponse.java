package com.bakery.bakery.security.dto;

import java.util.List;

import com.bakery.bakery.cake.dto.CakeResponse;
import com.bakery.bakery.pie.dto.PieResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BakerResponse extends PersonResponse{
    private String dni;
    private String docDNI;
    private String ruc;
    private String docRUC;
    private String docSanitation;
    private String permMunicipa;
    private String license;
    private List<CakeResponse> cakes;
    private List<PieResponse> pies;
}
