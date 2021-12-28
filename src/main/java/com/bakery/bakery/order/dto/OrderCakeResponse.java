package com.bakery.bakery.order.dto;

import com.bakery.bakery.cake.dto.CakeResponse;

import lombok.Data;

@Data
public class OrderCakeResponse {
    private OrderResponse order;
    private CakeResponse cake;
    private Long quantify;
    private Double priceUnid;
}
