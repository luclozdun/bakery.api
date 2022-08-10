package com.bakery.bakery.checkout.dto;

import lombok.Data;

@Data
public class Item {
    private String name;
    private String quantity;
    private ValueCurrency unit_amount;
}
