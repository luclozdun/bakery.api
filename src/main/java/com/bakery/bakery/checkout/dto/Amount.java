package com.bakery.bakery.checkout.dto;

import lombok.Data;

@Data
public class Amount {
    private String value;
    private String currency_code;
    private Breakdown breakdown;
}