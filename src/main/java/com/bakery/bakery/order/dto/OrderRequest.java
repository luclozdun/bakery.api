package com.bakery.bakery.order.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
    private Double delivery;
    private Double admCost;
    private Long customerId;
    private Long bakerId;
    private List<OrderProductId> cakeId;
    private List<OrderProductId> piesId;
}
