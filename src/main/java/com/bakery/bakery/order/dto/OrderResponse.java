package com.bakery.bakery.order.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderResponse {
    private List<OrderCakeResponse> orderCakes;
    private List<OrderPieResponse> orderPies;
}
