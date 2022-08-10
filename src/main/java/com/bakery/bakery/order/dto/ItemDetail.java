package com.bakery.bakery.order.dto;

import java.util.List;

import lombok.Data;

@Data
public class ItemDetail {
    private Double admCost;
    private Double delivery;
    private List<ItemDescription> items;
    private BakerSimpleResponse baker;
    private CustomerSimpleResponse customer;
}
