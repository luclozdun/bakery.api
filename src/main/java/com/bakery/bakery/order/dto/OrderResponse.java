package com.bakery.bakery.order.dto;

import java.util.List;

import com.bakery.bakery.order.model.OrderCake;
import com.bakery.bakery.order.model.OrderPie;

import lombok.Data;

@Data
public class OrderResponse {
    private List<OrderCake> orderCakes;
    private List<OrderPie> orderPies;
    private Double total;
}
