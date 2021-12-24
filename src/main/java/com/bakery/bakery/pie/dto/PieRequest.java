package com.bakery.bakery.pie.dto;

import lombok.Data;

@Data
public class PieRequest {
    private Long coverPieId;
    private Long formPieId;
    private Long sizePieId;
    private Long typeDoughtId;
    private Long typePieId;
    private Long bakerId;
    private Long quantify;
    private Double price;
}