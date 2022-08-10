package com.bakery.bakery.checkout.dto;

import java.util.List;

import lombok.Data;

@Data
public class PurchaseUnits {
    private String description;
    private Amount amount;
    private List<Item> items;
}
