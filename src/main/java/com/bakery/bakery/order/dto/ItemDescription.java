package com.bakery.bakery.order.dto;

import lombok.Data;

@Data
public class ItemDescription {
    private String name;
    private Long id;
    private Double price;
    private Long quantity;
    private ItemType type;
}