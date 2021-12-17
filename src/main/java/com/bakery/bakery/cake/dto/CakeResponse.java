package com.bakery.bakery.cake.dto;

import java.util.List;

import lombok.Data;

@Data
public class CakeResponse {
    private Long id;
    private Long quantify;
    private Double price;
    private CoverCakeResponse coverCake;
    private SizeCakeResponse sizeCake;
    private TasteCakeResponse tasteCake;
    private TypeCakeResponse typeCake;
    private List<FillerCakeResponse> fillerCakes;
}
