package com.bakery.bakery.pie.dto;

import lombok.*;

@Data
public class PieResponse {
    private Long id;
    private CoverPieResponse coverPie;
    private FormPieResponse formPie;
    private SizePieResponse sizePie;
    private TypeDoughtResponse typeDought;
    private TypePieResponse typePie;
    private Long quantify;
    private Double price;
}
