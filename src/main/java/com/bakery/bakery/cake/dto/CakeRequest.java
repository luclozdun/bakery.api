package com.bakery.bakery.cake.dto;

import java.util.List;

import lombok.Data;

@Data
public class CakeRequest {
    private Long typecakeId;
    private Long covercakeId;
    private Long sizecakeId;
    private Long tastecakeId;
    private Long quantify;
    private Double price;
    private Long bakerId;
    private List<Long> fillerCakeIds;
}