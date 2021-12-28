package com.bakery.bakery.order.dto;

import com.bakery.bakery.pie.dto.PieResponse;

import lombok.Data;

@Data
public class OrderPieResponse {
    private PieResponse pie;
    private Long quantify;
    private Double price;
}
